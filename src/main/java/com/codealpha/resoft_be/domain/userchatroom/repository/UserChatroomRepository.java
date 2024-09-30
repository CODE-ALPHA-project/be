package com.codealpha.resoft_be.domain.userchatroom.repository;

import com.codealpha.resoft_be.domain.userchatroom.entity.UserChatroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChatroomRepository extends JpaRepository<UserChatroom, Long> {
}
