package com.codealpha.resoft_be.domain.message.service.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class MessageBasedHumanStrategy implements  MessageStrategy{

    private final SimpMessagingTemplate messagingTemplate;
    /**
     * 현재 방에 속해있는 사람들 전체에게 전송 Socket을 통해서
     * @param target
     * @param message
     * @return
     */
    @Override
    public Mono<Void> sendMessage(String target, String message) {
        messagingTemplate.convertAndSend("/topic/answers/" + target, message);
        return Mono.empty();
    }
}
