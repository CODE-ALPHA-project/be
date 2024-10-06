package com.codealpha.resoft_be.domain.message.repository;

import com.codealpha.resoft_be.domain.message.entity.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends ReactiveMongoRepository<Message, String> {
    // 추가적인 쿼리 메서드를 정의할 수 있습니다.
}
