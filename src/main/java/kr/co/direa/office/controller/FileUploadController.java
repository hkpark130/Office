package kr.co.direa.office.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FileUploadController {
    private static final String UPLOAD_DIR = "uploads";

    @PostMapping(value = "/upload", consumes = { "multipart/form-data" })
    ResponseEntity<?> deviceApplication(
            @RequestPart("file") MultipartFile file
    ) {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            // 파일 저장 경로 설정
            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            // 파일 저장
            Files.copy(file.getInputStream(), filePath);
            // 파일 업로드 성공 메시지 반환
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the file");
        }
    }

    @PostMapping(value = "/upload-mock", consumes = { "multipart/form-data" })
    ResponseEntity<?> uploadMock(
            @RequestPart("file") MultipartFile file
    ) {
        return ResponseEntity.ok("Success.");
    }

}
