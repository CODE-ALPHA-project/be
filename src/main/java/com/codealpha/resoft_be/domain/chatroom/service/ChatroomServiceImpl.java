package com.codealpha.resoft_be.domain.chatroom.service;

import com.codealpha.resoft_be.domain.chatroom.dto.Request;
import com.codealpha.resoft_be.domain.chatroom.entity.Chatroom;
import com.codealpha.resoft_be.domain.chatroom.repository.ChatroomRepository;
import com.codealpha.resoft_be.domain.user.entity.User;
import com.codealpha.resoft_be.domain.user.exception.UserNotFoundException;
import com.codealpha.resoft_be.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatroomServiceImpl implements ChatroomService{
    private final UserRepository userRepository;
    private final ChatroomRepository chatroomRepository;
    @Override
    public List<Chatroom> getAllChatrooms(Long userId) {
        return chatroomRepository.findAllByParticipant_Id(userId);
    }

    @Override
    public Chatroom createChatroom(Request.Create createCmd) {
        User foundUser = userRepository.findById(createCmd.getUserId())
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        Chatroom chatroom = Chatroom.create(createCmd.getName(), createCmd.getType(), createCmd.getStatus());
        chatroom.addParticipant(foundUser);
        return chatroomRepository.save(chatroom);
    }

    @Override
    public void deleteChatroom(Long chatroomId) {
        chatroomRepository.deleteById(chatroomId);
    }
}
