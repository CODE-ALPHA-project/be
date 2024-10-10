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
    @Builder.Default
    @JoinColumn(name = "user_id")
    User participant = null;

    public static Chatroom create(String name, String type, String status){
        return Chatroom.builder()
                .name(name)
                .type(ChatroomType.valueOf(type))
                .status(ChatroomStatus.valueOf(status))
                .build();
    }
    public void addParticipant(User participant){
        participant.addChatroomList(this);
        this.participant = participant;
    }
}
