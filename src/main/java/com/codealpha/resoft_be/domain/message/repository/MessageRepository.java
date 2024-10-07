package com.codealpha.resoft_be.domain.message.repository;

import com.codealpha.resoft_be.domain.message.entity.Message;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MessageRepository extends ReactiveMongoRepository<Message, ObjectId> {
    // 추가적인 쿼리 메서드를 정의할 수 있습니다.

    Flux<Message> findAllByChatRoomId(Long chatRoomId);
}
