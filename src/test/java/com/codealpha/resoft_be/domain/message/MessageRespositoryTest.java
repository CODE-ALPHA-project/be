package com.codealpha.resoft_be.domain.message;

import com.codealpha.resoft_be.domain.message.entity.Message;
import com.codealpha.resoft_be.domain.message.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    @BeforeEach
    void setUp() {
        messageRepository.deleteAll().block();
    }

    Message createDummyMessage(String content, Long chatRoomId) {
        return Message.builder()
                .message(content)
                .chatRoomId(chatRoomId)
                .build();
    }

    @Nested
    @DisplayName("메시지 저장 테스트")
    class 메시지저장테스트 {

        @Test
        @DisplayName("저장된 메시지를 반환해야 한다")
        void 메시지저장_저장된메시지를반환해야한다() {
            //given
            Message message = createDummyMessage("안녕하세요", 1L);

            //when
            Mono<Message> savedMessage = messageRepository.save(message);

            //then
            StepVerifier.create(savedMessage)
                    .expectNextMatches(msg -> msg.getMessage().equals("안녕하세요"))
                    .verifyComplete();
        }

        @Test
        @DisplayName("중복 채팅방 ID로 메시지를 저장하면 두 메시지를 모두 저장해야 한다")
        void 중복채팅방ID로메시지저장_두메시지를모두저장해야한다() {
            // given
            Message message1 = createDummyMessage("안녕하세요", 1L);
            Message message2 = createDummyMessage("안녕", 1L);

            // when
            Flux<Message> savedMessages = Flux.just(message1, message2)
                    .flatMap(messageRepository::save);

            // then
            StepVerifier.create(savedMessages)
                    .expectNextMatches(msg -> msg.getMessage().equals("안녕하세요"))
                    .expectNextMatches(msg -> msg.getMessage().equals("안녕"))
                    .verifyComplete();
        }
    }

    @Nested
    @DisplayName("메시지 조회 테스트")
    class 메시지조회테스트 {

        @Test
        @DisplayName("메시지 ID로 조회하면 메시지를 반환해야 한다")
        void 메시지ID로조회_메시지를반환해야한다() {
            // given
            Message message = createDummyMessage("안녕하세요", 1L);
            Message savedMessage = messageRepository.save(message).block();

            // when
            Mono<Message> foundMessage = messageRepository.findById(savedMessage.getId());

            // then
            StepVerifier.create(foundMessage)
                    .expectNextMatches(msg -> msg.getMessage().equals("안녕하세요"))
                    .verifyComplete();
        }

        @Test
        @DisplayName("존재하지 않는 메시지 ID로 조회하면 빈 결과를 반환해야 한다")
        void 메시지ID로조회_존재하지않으면빈결과를반환해야한다() {
            // when
            Mono<Message> foundMessage = messageRepository.findById("nonexistentId");

            // then
            StepVerifier.create(foundMessage)
                    .expectNextCount(0)
                    .verifyComplete();
        }
    }

    @Nested
    @DisplayName("메시지 삭제 테스트")
    class 메시지삭제테스트 {

        @Test
        @DisplayName("메시지를 삭제하면 메시지가 제거되어야 한다")
        void 메시지삭제_메시지가제거되어야한다() {
            // given
            Message message = createDummyMessage("안녕하세요", 1L);
            Message savedMessage = messageRepository.save(message).block();

            // when
            Mono<Void> deleteMessage = messageRepository.deleteById(savedMessage.getId());

            // then
            StepVerifier.create(deleteMessage)
                    .verifyComplete();

            // when
            Mono<Message> foundMessage = messageRepository.findById(savedMessage.getId());

            // then
            StepVerifier.create(foundMessage)
                    .expectNextCount(0)
                    .verifyComplete();
        }

        @Test
        @DisplayName("존재하지 않는 메시지를 삭제하면 오류 없이 완료되어야 한다")
        void 존재하지않는메시지삭제_오류없이완료되어야한다() {
            // when
            Mono<Void> deleteMessage = messageRepository.deleteById("nonexistentId");

            // then
            StepVerifier.create(deleteMessage)
                    .verifyComplete();
        }
    }

    @Nested
    @DisplayName("메시지 카운트 테스트")
    class 메시지카운트테스트 {

        @Test
        @DisplayName("메시지 카운트는 정확한 수를 반환해야 한다")
        void 메시지카운트_정확한수를반환해야한다() {
            // given
            Message message1 = createDummyMessage("안녕하세요", 1L);
            Message message2 = createDummyMessage("안녕", 2L);
            messageRepository.save(message1).block();
            messageRepository.save(message2).block();

            // when
            Mono<Long> count = messageRepository.count();

            // then
            StepVerifier.create(count)
                    .expectNext(2L)
                    .verifyComplete();
        }
    }
}
