package com.codealpha.resoft_be.api.v1.message;

import com.codealpha.resoft_be.domain.message.dto.Request;
import com.codealpha.resoft_be.domain.message.entity.Message;
import com.codealpha.resoft_be.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author knu-k
 * This Controller is used for reactive messaging
 */
@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    @MessageMapping("/send")
    public void send(Request.Send sendMessage) { // Change parameter type to DTO

        messageService.sendMessage(sendMessage).subscribe();

        String responseMessage = "Echo: " + sendMessage.getMessage();
        log.info(responseMessage);
        messagingTemplate.convertAndSend("/topic/messages", responseMessage);
    }

    @GetMapping()
    public Flux<Message> getMessages(@RequestParam Long chatroomId){
        return messageService.getAllMessages(chatroomId);
    }
}
