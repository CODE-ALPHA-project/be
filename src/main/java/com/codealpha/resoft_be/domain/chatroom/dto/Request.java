package com.codealpha.resoft_be.domain.chatroom.dto;

import lombok.Data;

public class Request {
    @Data
    public static class Create{
        String name;
        String type;
        String status;
        Long userId;
    }
}
