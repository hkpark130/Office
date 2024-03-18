package kr.co.direa.office;

import com.opencsv.exceptions.CsvException;
import kr.co.direa.office.util.DecryptRunner;
import kr.co.direa.office.util.Keycloak;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVReader;

@Slf4j
public class SyncSSO {
    public static void main(String[] args) { // CSV 파일로 동기화 (CSV -> Keycloak)
        String token = Keycloak.getAdminAccessToken(System.getenv("KEYCLOAK_URL"), System.getenv("ADMIN"), System.getenv("REALM"));

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeader = new HttpHeaders();
        String csvFile = System.getProperty("user.dir") + "/csvfile.csv";
        String keycloakUrl = System.getenv("KEYCLOAK_URL") + "/admin/realms/"+
                System.getenv("REALM")+"/users";

        requestHeader.setContentType(MediaType.APPLICATION_JSON);
        requestHeader.set("Authorization", token);

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            reader.readNext();
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                Map<String, Object> userMap = new HashMap<>();
                String username = lineInArray[1];
                userMap.put("enabled", true);
                userMap.put("emailVerified", true);
                userMap.put("email", lineInArray[0]+"@direa.co.kr");
                userMap.put("username", username);
                String departmentName = (!"".equals(lineInArray[3])) ? (!"".equals(lineInArray[4])? lineInArray[4] : lineInArray[3]) : null;
                userMap.put("attributes", new HashMap<String, Object>() {{
                    put("department", new String[]{departmentName});
                }});

//                userMap.put("groups", new String[]{"user"});
                userMap.put("credentials", new Object[]{new HashMap<String, Object>() {{
                    put("type", "password");
                    put("value", username);
                    put("temporary", false);
                }}});

                HttpEntity<Map<String, Object>> request = new HttpEntity<>(userMap, requestHeader);
                ResponseEntity<String> response = restTemplate.exchange(keycloakUrl, HttpMethod.POST, request, String.class);

                log.info("Response: " + response.getBody());
//                log.info("userMap: " + userMap);
            }
        } catch (IOException | CsvException e) {
            log.error("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
