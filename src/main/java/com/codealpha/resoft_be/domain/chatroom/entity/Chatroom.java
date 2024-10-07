package com.codealpha.resoft_be.domain.chatroom.entity;

import com.codealpha.resoft_be.common.entity.BaseEntity;
import com.codealpha.resoft_be.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "chatrooms")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chatroom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column()
    String name;

    @Column()
    @Enumerated(EnumType.STRING)
    ChatroomType type;

    @Column()
    @Enumerated(EnumType.STRING)
    ChatroomStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

//    public static Chatroom create(){
//        return Chatroom.builder()
//                .name()
//                .type()
//                .status()
//                .build();
//    }
}
