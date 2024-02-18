package kr.co.direa.office.constant;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Constants {
    public static final String ADMIN = "test";
    public static final String APPROVAL_COMPLETED = "승인완료";
    public static final String APPROVAL_WAITING = "승인대기";
    public static final String APPROVAL_REJECT = "반려";
    public static final String NORMAL_TYPE = "정상";
    public static final String OLD_TYPE = "노후";
    public static final String DISPOSE_TYPE = "폐기";
    public static final String APPROVAL_RETURN = "반납";
    public static final String APPROVAL_RENTAL = "대여";
    public static final String KEYCLOAK_URL = "http://192.168.0.46:8080";
}
