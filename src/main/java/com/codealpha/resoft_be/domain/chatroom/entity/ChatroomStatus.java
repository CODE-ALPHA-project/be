package com.codealpha.resoft_be.domain.chatroom.entity;

public enum ChatroomStatus {
    PROGRESS("진행"),
    COMPLETION("완료");

    private final String description;

    ChatroomStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
