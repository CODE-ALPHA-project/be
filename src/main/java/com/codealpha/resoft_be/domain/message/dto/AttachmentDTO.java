package com.codealpha.resoft_be.domain.message.dto;

import lombok.Data;

@Data
public class AttachmentDTO {
    private String fileName;
    private String fileType;
    private String fileUrl;
}
