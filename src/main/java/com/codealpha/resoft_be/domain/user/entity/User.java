package com.codealpha.resoft_be.domain.user.entity;

import com.codealpha.resoft_be.domain.userchatroom.entity.UserChatroom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column()
    String name;

    @Column()
    String email;

    @Column()
    String password;

    @OneToMany
    @Builder.Default
    List<UserChatroom> userChatroomList = new ArrayList<>();
}
