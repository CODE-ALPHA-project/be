package com.codealpha.resoft_be.domain.message.service;

import com.codealpha.resoft_be.domain.message.dto.Request;
import com.codealpha.resoft_be.domain.message.entity.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessageService {
    Mono<Message> saveMessage(Request.Send sendCmd);
    Flux<Message> getAllMessages(Long chatRoomId);
}
