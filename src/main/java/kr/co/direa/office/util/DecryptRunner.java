package kr.co.direa.office.util;

import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class DecryptRunner {
    private final StringEncryptor stringEncryptor;

    public String encrypt(String data) {
        return stringEncryptor.encrypt(data);
    }

    public String decrypt(String data) {
        return stringEncryptor.decrypt(data);
    }

}
