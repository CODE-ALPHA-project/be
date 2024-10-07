package com.codealpha.resoft_be.domain.chatroom.entity;

public enum ChatroomType {
    HUMAN("사람"),
    AI("인공지능");

    private final String description;

    ChatroomType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
