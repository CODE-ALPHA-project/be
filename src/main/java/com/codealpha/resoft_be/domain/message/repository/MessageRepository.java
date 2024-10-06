package com.codealpha.resoft_be.domain.message.repository;

import com.codealpha.resoft_be.domain.message.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    // 추가적인 쿼리 메서드를 정의할 수 있습니다.
}
