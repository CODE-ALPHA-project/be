package com.codealpha.resoft_be.domain.chatroom;

import com.codealpha.resoft_be.common.config.JpaAuditingConfig;
import com.codealpha.resoft_be.domain.user.entity.User;
import com.codealpha.resoft_be.domain.user.repository.UserRepository;
import com.codealpha.resoft_be.domain.chatroom.entity.Chatroom;
import com.codealpha.resoft_be.domain.chatroom.repository.ChatroomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
@DataJpaTest
@ActiveProfiles("local")
@Import(JpaAuditingConfig.class)
@ExtendWith(SpringExtension.class)
public class ChatroomRepositoryTest {

    @Autowired
    private ChatroomRepository chatroomRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private Chatroom chatroom;

    @BeforeEach
    void setup() {
        // Create and save a user for testing purposes
        user = User.create("name","email","password");
        userRepository.save(user);

        // Create and save a chatroom
        chatroom = Chatroom.create("name","HUMAN","PROGRESS");
        chatroom.addParticipant(user);
        chatroomRepository.save(chatroom);
    }

    @Test
    void testFindAllByParticipant_Id() {
        List<Chatroom> foundChatroom = chatroomRepository.findAllByParticipant_Id(user.getId());
        Assertions.assertEquals(foundChatroom.get(0),chatroom);
    }

}
