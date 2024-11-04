package com.codealpha.resoft_be.domain.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private static final String UPLOAD_DIR = "/upload";

    String upload(MultipartFile file){
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();  // 경로가 없으면 디렉토리 생성
        }

        try {
            // 저장할 파일 경로 설정
            Path filePath = Paths.get(UPLOAD_DIR, file.getOriginalFilename());
            Files.write(filePath, file.getBytes());  // 파일 저장
            return "File saved successfully: " + filePath.toAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return "File upload failed";
        }
    }

    List<String> uploadBulk(){
        return List.of("url1","url2");
    }
}
