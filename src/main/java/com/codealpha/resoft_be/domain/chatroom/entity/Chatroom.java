package com.codealpha.resoft_be.domain.chatroom.entity;

import com.codealpha.resoft_be.domain.message.entity.Message;
import com.codealpha.resoft_be.domain.userchatroom.entity.UserChatroom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Chatrooms")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chatroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column()
    String name;
    //enum
    @Column()
    String type;
    //enum
    @Column()
    String status;

    @OneToOne
    UserChatroom userChatroom = null;

    @OneToMany
    @Builder.Default
    List<Message> messages = new ArrayList<>();
}
