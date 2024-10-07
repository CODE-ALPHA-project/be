package com.codealpha.resoft_be.domain.message.service;

import com.codealpha.resoft_be.domain.message.dto.Request;
import com.codealpha.resoft_be.domain.message.entity.Message;
import com.codealpha.resoft_be.domain.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    @Override
    public Mono<Message> saveMessage(Request.Send sendCmd) {
        Message message = Message.createOnlyMessage(sendCmd.getChatRoomId(), sendCmd.getMessage());
        return messageRepository.save(message);
    }

    @Override
    public Flux<Message> getAllMessages(Long chatRoomId) {
        return messageRepository.findAllByChatRoomId(chatRoomId);
    }
}
