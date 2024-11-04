package com.codealpha.resoft_be.domain.message.entity;

import com.codealpha.resoft_be.domain.message.dto.LawReferenceDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@Document("law_reference")
public class LawReference {
    private String law;
    private String chapter;
    private String title;

    public LawReference(String law, String chapter, String title) {
        this.law = law;
        this.chapter = chapter;
        this.title = title;
    }
    public static LawReference from(LawReferenceDTO lawReferenceDTO){
        return new LawReference(lawReferenceDTO.getLaw(), lawReferenceDTO.getChapter(), lawReferenceDTO.getTitle());
    }
    public String toString() {
        return "LawReference{" +
                "law='" + law + '\'' +
                ", chapter='" + chapter + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
