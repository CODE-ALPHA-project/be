package com.codealpha.resoft_be.domain.message;

import com.codealpha.resoft_be.domain.message.dto.Request;
import com.codealpha.resoft_be.domain.message.entity.Message;
import com.codealpha.resoft_be.domain.message.repository.MessageRepository;
import com.codealpha.resoft_be.domain.message.service.MessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MessageServiceTest {

    private MessageRepository messageRepository;
    private MessageServiceImpl messageService;

    @BeforeEach
    public void 설정_테스트() { // setUp을 한글로 변경
        messageRepository = Mockito.mock(MessageRepository.class);
        messageService = new MessageServiceImpl(messageRepository);
    }

    @Test
    @DisplayName("메시지를 성공적으로 저장하는 테스트")
    void 메시지_저장_테스트() { // testSaveMessage를 한글로 변경
        // Given
        Long chatRoomId = 1L;
        Request.Send sendCmd = new Request.Send(1L,"Hello, World!",null); // 변수 이름은 영어로 유지
        Message savedMessage = Message.createOnlyMessage(chatRoomId, sendCmd.getMessage());

        when(messageRepository.save(any(Message.class))).thenReturn(Mono.just(savedMessage));

        // When
        Mono<Message> result = messageService.saveMessage(sendCmd);

        // Then
        StepVerifier.create(result)
                .expectNext(savedMessage)
                .verifyComplete();

        verify(messageRepository, times(1)).save(any(Message.class));
    }
    @Test
    @DisplayName("전체 메시지를 성공적으로 조회하는 테스트")
    void 메시지_전체_조회_테스트() {
        // Given
        Long chatRoomId = 1L;
        Message message1 = Message.createOnlyMessage(chatRoomId, "Hello, World!");
        Message message2 = Message.createOnlyMessage(chatRoomId, "Second message");

        when(messageRepository.findAllByChatRoomId(chatRoomId)).thenReturn(Flux.just(message1, message2));

        // When
        Flux<Message> result = messageService.getAllMessages(chatRoomId); // 전체 메시지 조회 메서드 사용

        // Then
        StepVerifier.create(result)
                .expectNext(message1)
                .expectNext(message2)
                .verifyComplete();

        verify(messageRepository, times(1)).findAllByChatRoomId(chatRoomId); // findByChatRoomId가 호출되었는지 검증
    }
}
