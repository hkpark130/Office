package kr.co.direa.office.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
public class Keycloak {
    public static String getAdminAccessToken(String keycloakUrl, String admin, String realm) {
        try {
            String tokenUrl = keycloakUrl + "/realms/"+realm+"/protocol/openid-connect/token";

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders requestHeader = new HttpHeaders();
            requestHeader.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("grant_type", "password");
            requestBody.add("client_id", "admin-cli");
            requestBody.add("username", admin);
            requestBody.add("password", "test");

            HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(requestBody, requestHeader);
            ResponseEntity<Map> tokenResponse = restTemplate.exchange(tokenUrl, HttpMethod.POST, tokenRequest, Map.class);
            return "Bearer " + tokenResponse.getBody().get("access_token").toString();
        } catch (Exception e) {
            log.error("Exception : " + e);
            e.printStackTrace();
        }
        return null;
    }

}
