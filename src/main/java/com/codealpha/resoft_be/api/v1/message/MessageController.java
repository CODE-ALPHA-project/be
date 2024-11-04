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
    public void send(Request.Send sendMessage) {
        // 메시지를 비동기적으로 저장하고 결과에 따라 처리합니다.
        messageService.sendMessage(sendMessage)
                .doOnSuccess(savedMessage -> {
                    log.info("Message saved successfully: {}", savedMessage);
                    // 저장된 메시지에 대한 응답을 클라이언트에 보냅니다.

                    messagingTemplate.convertAndSend("/topic/messages", savedMessage);
                })
                .doOnError(error -> {
                    log.error("Error occurred while saving message: ", error);
                    // 에러 발생 시 클라이언트에 에러 메시지를 보낼 수 있습니다.
                    messagingTemplate.convertAndSend("/topic/messages", "Error saving message: " + error.getMessage());
                })
                .subscribe();
    }


    @GetMapping()
    public Flux<Message> getMessages(@RequestParam Long chatroomId){
        return messageService.getAllMessages(chatroomId);
    }
}
