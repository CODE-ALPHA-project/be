package com.codealpha.resoft_be.domain.message.service;

import com.codealpha.resoft_be.domain.message.dto.AnswerResponseDTO;
import com.codealpha.resoft_be.domain.message.dto.LawReferenceDTO;
import com.codealpha.resoft_be.domain.message.dto.Request;
import com.codealpha.resoft_be.domain.message.entity.Message;
import com.codealpha.resoft_be.domain.message.entity.LawReference;
import com.codealpha.resoft_be.domain.message.repository.MessageRepository;
import com.codealpha.resoft_be.domain.message.service.factory.MessageStrategyFactory;
import com.codealpha.resoft_be.domain.message.service.strategy.MessageStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageStrategyFactory messageFactory;
    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Mono<Message> sendMessage(Request.Send sendCmd) {
        Message message = Message.createHumanMessage(sendCmd.getUserId(), sendCmd.getChatRoomId(), sendCmd.getMessage());

        return messageRepository.save(message)
                .doOnSuccess(savedMessage -> log.info("Message saved successfully: {}", savedMessage))
                .doOnError(error -> log.error("Error occurred while saving message: ", error))
                .flatMap(savedMessage ->{
                    MessageStrategy strategy = messageFactory.createMessageStrategy(sendCmd.getSentType());
                    return strategy.sendMessage(sendCmd.getChatRoomId().toString(), savedMessage.getMessage())
                            .then(Mono.just(savedMessage));
                })
                .doOnError(e->log.error("Failed"));
    }

    @Override
    public Flux<Message> getAllMessages(Long chatRoomId) {
        log.info("Retrieving all messages for chat room: {}", chatRoomId);
        return messageRepository.findAllByChatRoomId(chatRoomId);
    }

    @KafkaListener(topics = "ai-responses", groupId = "your_group", containerFactory = "kafkaListenerContainerFactory")
    public void receiveMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        String message = record.value();
        String key = record.key();

        log.info("Received Message: {}", message);
        log.info("Received Key: {}", key);

        try {
            AnswerResponseDTO answerResponse = objectMapper.readValue(message, AnswerResponseDTO.class);
            List<LawReference> lawReferences = getLawReferenceList(answerResponse.getReferences());

            Message aiMessage = Message.createAIMessage(Long.valueOf(key), answerResponse.getAnswer(), lawReferences);

            // Mono가 완료된 후 오프셋 커밋
            messageRepository.save(aiMessage)
                    .doOnSuccess(saved -> acknowledgment.acknowledge())  // 성공적으로 저장되면 커밋
                    .doOnError(e -> log.error("Error saving to repository: {}", e.getMessage(), e))
                    .subscribe();

            log.info("Decoded Answer: {}", answerResponse.getAnswer());
            messagingTemplate.convertAndSend("/topic/answers/" + key, answerResponse);
        } catch (Exception e) {
            log.error("Error decoding message: {}", e.getMessage(), e);
        }
    }




    private List<LawReference> getLawReferenceList(List<LawReferenceDTO> referenceDTOList) {
        return referenceDTOList.stream()
                .map(LawReference::from)
                .toList();
    }
}

//redis, mongo -> docker 안쓰지마 / aws 완전 배포