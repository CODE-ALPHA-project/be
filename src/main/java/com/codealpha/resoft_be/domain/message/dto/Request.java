package com.codealpha.resoft_be.domain.message.dto;

import com.codealpha.resoft_be.domain.message.service.strategy.MessageStrategyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class Request {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Send{
        private Long userId;
        private Long chatRoomId;
        private String message;
        private String sentType;
        private List<AttachmentDTO> attachmentList;
    }

}
