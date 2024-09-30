package com.codealpha.resoft_be.domain.userchatroom.entity;

import com.codealpha.resoft_be.domain.chatroom.entity.Chatroom;
import com.codealpha.resoft_be.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserChatroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    User user;

    @OneToOne
    Chatroom chatroom;

}
