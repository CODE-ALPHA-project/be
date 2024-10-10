package com.codealpha.resoft_be.domain.user.entity;

import com.codealpha.resoft_be.common.entity.BaseEntityWithUpdate;
import com.codealpha.resoft_be.domain.chatroom.entity.Chatroom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name="users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntityWithUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column()
    String name;

    @Column()
    String email;

    @Column()
    String password;

    @OneToMany(mappedBy = "participant")
    @Builder.Default
    List<Chatroom> chatroomList = new ArrayList<>();

    public void addChatroomList(Chatroom chatroom){
        chatroomList.add(chatroom);
    }
    public static User create(String name, String email, String password){
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
