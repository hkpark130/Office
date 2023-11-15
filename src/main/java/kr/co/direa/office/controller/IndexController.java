package kr.co.direa.office.controller;

import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class IndexController {
    @GetMapping("/health")
    public String status() {
        return "It's working.";
    }

    @GetMapping(value = "/devices")
    ResponseEntity<?> echoReturn() throws IOException {
        Path filePath = Path.of(System.getProperty("user.dir") + "/test3.json");
        String jsonContent = Files.readString(filePath);
        return ResponseEntity.ok(
                jsonContent
        );
    }

    @GetMapping("/error")
    public String error() {
        throw new IllegalArgumentException("error test");
    }

    @GetMapping("/custom_error")
    public String customError() {
        throw new CustomException(CustomErrorCode.METHOD_NOT_ALLOWED);
    }

}
