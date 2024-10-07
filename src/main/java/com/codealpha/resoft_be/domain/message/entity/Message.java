package com.codealpha.resoft_be.domain.message.entity;

import com.codealpha.resoft_be.domain.chatroom.entity.Chatroom;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "messages")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id  // MongoDB의 고유 식별자 필드
    private ObjectId id;  // MongoDB에서 기본적으로 String 타입의 _id를 사용하므로 변경

    private String message;
    private List<Attachment> attachmentList;
    private Long chatRoomId;

    public static Message createOnlyMessage(Long chatRoomId, String message){
        return Message.builder()
                .chatRoomId(chatRoomId)
                .message(message)
                .build();
    }

    public static Message createMessageAndAttachmentList(Long chatRoomId, String message, List<Attachment> attachmentList){
        return Message.builder()
                .chatRoomId(chatRoomId)
                .message(message)
                .attachmentList(attachmentList)
                .build();
    }
}
