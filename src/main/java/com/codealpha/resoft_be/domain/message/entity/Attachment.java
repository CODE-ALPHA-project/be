package com.codealpha.resoft_be.domain.message.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "attachment")
public class Attachment {
    private String fileName;
    private String fileType;
    private String fileUrl;
}