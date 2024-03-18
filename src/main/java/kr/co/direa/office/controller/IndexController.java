package kr.co.direa.office.controller;

import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import kr.co.direa.office.util.DecryptRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class IndexController {
    private final DecryptRunner decryptRunner;
    @GetMapping("/health")
    public String status() {
        return "It's working.";
    }

    @GetMapping("/encrypt/{data}")
    public String encrypt(@PathVariable String data) {
        return decryptRunner.encrypt(data);
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
