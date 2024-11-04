package com.codealpha.resoft_be.domain.message.entity;

import java.util.List;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    private ObjectId id;
    private SenderType senderType;
    private Long chatRoomId;

    //Human
    private Long userId;
    private String message;
    private List<Attachment> attachmentList;

    //ai
    private List<LawReference> references;

    public static Message createAIMessage(Long chatroomId, String answer, List<LawReference> references) {
        return Message.builder()
                .chatRoomId(chatroomId)
                .senderType(SenderType.AI)
                .message(answer)
                .references(references)
                .build();
    }
    public static Message createHumanMessage(Long userId, Long chatroomId, String message) {
        return Message.builder()
                .userId(userId)
                .chatRoomId(chatroomId)
                .senderType(SenderType.HUMAN)
                .message(message)
                .build();
    }
}
