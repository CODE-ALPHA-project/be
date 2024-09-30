package com.codealpha.resoft_be.domain.message.repository;

import com.codealpha.resoft_be.domain.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
