package com.codealpha.resoft_be.domain.message.dto;

import java.util.List;
import lombok.Data;

@Data
public class AnswerResponseDTO {
    private String answer;
    private List<LawReferenceDTO> references;

    @Override
    public String toString() {
        return "AnswerResponse{" +
                "answer='" + answer + '\'' +
                ", references=" + references +
                '}';
    }
}
