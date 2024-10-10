package com.codealpha.resoft_be.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Request {
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class Register{
        String name;
        String email;
        String password;
    }
}
