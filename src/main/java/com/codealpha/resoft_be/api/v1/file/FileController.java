package com.codealpha.resoft_be.api.v1.file;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {
    @PostMapping("/upload")
    public String upload(){
        return null;
    }
}
