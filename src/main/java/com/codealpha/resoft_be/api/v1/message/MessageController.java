package com.codealpha.resoft_be.api.v1.message;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

/**
 * @author knu-k
 * this Controller is used for reactive messaging
 */
@Controller
@RequiredArgsConstructor
public class MessageController {
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public Mono<String> a(String message) {
        String responseMessage = "Echo: " + message;
        messagingTemplate.convertAndSend("/topic/messages", responseMessage);
        return Mono.just("하이");
    }
}
