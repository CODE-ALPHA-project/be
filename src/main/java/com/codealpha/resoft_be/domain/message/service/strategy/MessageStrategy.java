package com.codealpha.resoft_be.domain.message.service.strategy;

import reactor.core.publisher.Mono;

public interface MessageStrategy {
    Mono<Void> sendMessage(String target,String message);
}
