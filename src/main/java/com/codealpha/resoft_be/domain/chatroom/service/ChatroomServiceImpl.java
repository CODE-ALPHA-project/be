package com.codealpha.resoft_be.domain.chatroom.service;

import com.codealpha.resoft_be.domain.chatroom.dto.Request;
import com.codealpha.resoft_be.domain.chatroom.entity.Chatroom;
import com.codealpha.resoft_be.domain.chatroom.entity.ChatroomStatus;
import com.codealpha.resoft_be.domain.chatroom.entity.ChatroomType;
import com.codealpha.resoft_be.domain.chatroom.repository.ChatroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatroomServiceImpl implements ChatroomService{
    private final ChatroomRepository chatroomRepository;
    @Override
    public List<Chatroom> getAllChatrooms(Long userId) {
        return chatroomRepository.findAllByUserId(userId);
    }

    @Override
    public Chatroom createChatroom(Request.Create createCmd) {
        //User 조회 로직 추가
        //관계주입
        Chatroom chatroom = Chatroom.builder()
                .name(createCmd.getName())
                .type(ChatroomType.valueOf(createCmd.getType()))
                .status(ChatroomStatus.valueOf(createCmd.getStatus()))
                .build();
        return chatroomRepository.save(chatroom);
    }

    @Override
    public void deleteChatroom(Long chatroomId) {
        chatroomRepository.deleteById(chatroomId);
    }
}
