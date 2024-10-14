package com.codealpha.resoft_be.domain.message.service;

import com.codealpha.resoft_be.domain.message.dto.Request;
import com.codealpha.resoft_be.domain.message.entity.Message;
import com.codealpha.resoft_be.domain.message.repository.MessageRepository;
import com.codealpha.resoft_be.domain.message.service.factory.MessageStrategyFactory;
import com.codealpha.resoft_be.domain.message.service.strategy.MessageStrategy;
import com.codealpha.resoft_be.domain.message.service.strategy.MessageStrategyType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageStrategyFactory messageFactory;
    private final MessageRepository messageRepository;
    @Override
    public Mono<Message> saveMessage(Request.Send sendCmd) {
        Message message = Message.createOnlyMessage(sendCmd.getChatRoomId(), sendCmd.getMessage());
        MessageStrategy messageStrategy = messageFactory.createMessageStrategy(MessageStrategyType.AI);

        return messageStrategy
                .sendMessage("aa", message.getMessage())
                .then(Mono.just(message))  // 메시지가 성공적으로 전송된 후 메시지를 반환
                .doOnError(e -> log.error("Failed to send message: {}", e.getMessage()));
    }

    @Override
    public Flux<Message> getAllMessages(Long chatRoomId) {
        return messageRepository.findAllByChatRoomId(chatRoomId);
    }
}
