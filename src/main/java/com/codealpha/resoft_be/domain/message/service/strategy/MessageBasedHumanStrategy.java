package com.codealpha.resoft_be.domain.message.service.strategy;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
public class MessageBasedHumanStrategy implements  MessageStrategy{
    @Override
    public Mono<Void> sendMessage(String target, String message) {
        return null;
    }
}
