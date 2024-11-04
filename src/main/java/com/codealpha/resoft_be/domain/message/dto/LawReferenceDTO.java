package com.codealpha.resoft_be.domain.message.dto;

import lombok.Data;

@Data
public class LawReferenceDTO {
    private String law;
    private String chapter;
    private String title;
    @Override
    public String toString() {
        return "LawReference{" +
                "law='" + law + '\'' +
                ", chapter='" + chapter + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
