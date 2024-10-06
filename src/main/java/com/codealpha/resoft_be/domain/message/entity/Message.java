package com.codealpha.resoft_be.domain.message.entity;

import com.codealpha.resoft_be.domain.chatroom.entity.Chatroom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")  // MongoDB에서 해당 클래스는 "messages" 컬렉션에 매핑됩니다.
@Data  // @Getter, @Setter, @ToString, @EqualsAndHashCode를 한번에 처리
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id  // MongoDB의 고유 식별자 필드
    private String id;  // MongoDB에서 기본적으로 String 타입의 _id를 사용하므로 변경

    private String message;

    private Long chatRoomId;
}
