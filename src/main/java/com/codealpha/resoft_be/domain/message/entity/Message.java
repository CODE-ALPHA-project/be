package com.codealpha.resoft_be.domain.message.entity;

import com.codealpha.resoft_be.domain.chatroom.entity.Chatroom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "messages")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column()
    String message;

    @ManyToOne
    @Builder.Default
    Chatroom chatroom = null;
}
