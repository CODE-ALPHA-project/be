package com.codealpha.resoft_be.domain.chatroom.service;

import com.codealpha.resoft_be.domain.chatroom.dto.Request;
import com.codealpha.resoft_be.domain.chatroom.entity.Chatroom;

import java.util.List;

public interface ChatroomService {
    List<Chatroom> getAllChatrooms(Long userId);
    Chatroom createChatroom(Request.Create createCmd);
    void deleteChatroom(Long id);
}
