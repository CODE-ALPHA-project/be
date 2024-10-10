package com.codealpha.resoft_be.domain.chatroom.repository;

import com.codealpha.resoft_be.domain.chatroom.entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
    List<Chatroom> findAllByParticipant_Id(Long id);
}
