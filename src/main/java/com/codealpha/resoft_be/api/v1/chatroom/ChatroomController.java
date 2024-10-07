package com.codealpha.resoft_be.api.v1.chatroom;

import com.codealpha.resoft_be.domain.chatroom.dto.Request;
import com.codealpha.resoft_be.domain.chatroom.entity.Chatroom;
import com.codealpha.resoft_be.domain.chatroom.service.ChatroomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chatroom")
@AllArgsConstructor
public class ChatroomController {
    private final ChatroomService chatroomService;

    @GetMapping("/{userId}")
    public List<Chatroom> getAllChatrooms(@PathVariable Long userId){
        return chatroomService.getAllChatrooms(userId);
    }

    @PostMapping()
    public Chatroom createChatroom(@RequestBody Request.Create request) {
        return chatroomService.createChatroom(request);
    }

    @DeleteMapping ("/{chatroomId}")
    public void deleteChatroom(@PathVariable Long chatroomId) {
        chatroomService.deleteChatroom(chatroomId);
    }


}
