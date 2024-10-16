package com.codealpha.resoft_be.api.v1.message;

import com.codealpha.resoft_be.domain.message.dto.Request;
import com.codealpha.resoft_be.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

/**
 * @author knu-k
 * This Controller is used for reactive messaging
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    @MessageMapping("/send")
//        @SendTo("/topic/messages")
    public void send(Request.Send sendMessage) { // Change parameter type to DTO

        messageService.saveMessage(sendMessage).subscribe();

        String responseMessage = "Echo: " + sendMessage.getMessage();
        log.info(responseMessage);
        messagingTemplate.convertAndSend("/topic/messages", responseMessage);
    }
}
