package com.codealpha.resoft_be.domain.message.service;

import com.codealpha.resoft_be.domain.message.dto.AnswerResponse;
import com.codealpha.resoft_be.domain.message.dto.Request;
import com.codealpha.resoft_be.domain.message.entity.Message;
import com.codealpha.resoft_be.domain.message.repository.MessageRepository;
import com.codealpha.resoft_be.domain.message.service.factory.MessageStrategyFactory;
import com.codealpha.resoft_be.domain.message.service.strategy.MessageStrategy;
import com.codealpha.resoft_be.domain.message.service.strategy.MessageStrategyType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageStrategyFactory messageFactory;
    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Message> sendMessage(Request.Send sendCmd) {
        Message message = Message.createOnlyMessage(sendCmd.getChatRoomId(), sendCmd.getMessage());

        return messageRepository.save(message)
                .doOnSuccess(savedMessage -> log.info("Message saved successfully: {}", savedMessage))
                .doOnError(error -> log.error("Error occurred while saving message: ", error))
                .flatMap(savedMessage -> {
                    MessageStrategy messageStrategy = messageFactory.createMessageStrategy(MessageStrategyType.AI);
                    return messageStrategy.sendMessage(sendCmd.getChatRoomId().toString(), savedMessage.getMessage())
                            .then(Mono.just(savedMessage));  // 메시지 전송 성공 후 저장된 메시지 반환
                })
                .doOnError(e -> log.error("Failed to send message: {}", e.getMessage()));
    }
    @Override
    public Flux<Message> getAllMessages(Long chatRoomId) {
        log.info(messageRepository.findAll().then().toString());
        return messageRepository.findAllByChatRoomId(chatRoomId);
    }

    @KafkaListener(topics = "ai-responses", groupId = "your_group")
    public void receiveMessage(ConsumerRecord<String, String> record) {
        String message = record.value();  // 메시지 본문
        String key = record.key();  // 메시지 키

        log.error("Received Message: " + message);
        log.error("Received Key: " + key);

        try {
            AnswerResponse myMessage = objectMapper.readValue(message, AnswerResponse.class);
            log.error("Decoded Answer: " + myMessage.getAnswer());
            messagingTemplate.convertAndSend("/topic/answers/"+key, myMessage);
        } catch (Exception e) {
            e.printStackTrace(); // 예외 처리
        }
    }
}
