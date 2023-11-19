package kr.co.direa.office.controller;

import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
//@CrossOrigin(origins = {"http://192.168.2.110:8080", "http://192.168.2.110", "http://192.168.2.111:8080"}) // 개발용
@CrossOrigin(origins = {"http://172.30.1.30:8080", "http://172.30.1.30"})
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

    @GetMapping(path = "/test")
    public String index(Principal principal) {
        OAuth2User user = ((OAuth2User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return user.toString();
    }

}