package com.codealpha.resoft_be.domain.message.dto;

import java.util.List;
import lombok.Data;

@Data
public class AnswerResponse {
    private String answer;
    private List<LawReference> references;

    @Override
    public String toString() {
        return "AnswerResponse{" +
                "answer='" + answer + '\'' +
                ", references=" + references +
                '}';
    }
}
