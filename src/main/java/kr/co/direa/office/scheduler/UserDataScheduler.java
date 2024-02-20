package kr.co.direa.office.scheduler;

import kr.co.direa.office.domain.Departments;
import kr.co.direa.office.domain.Users;
import kr.co.direa.office.dto.UserDto;
import kr.co.direa.office.repository.UsersRepository;
import kr.co.direa.office.service.DepartmentsService;
import kr.co.direa.office.service.UsersService;
import kr.co.direa.office.util.Keycloak;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static kr.co.direa.office.constant.Constants.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserDataScheduler {
    private final UsersService usersService;
    private final DepartmentsService departmentsService;

    private List<Map> getResponseBody(ResponseEntity<List<Map>> response) {
        if (response != null && response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }
    @Scheduled(cron = "*/5 * * * * *")
//    @Scheduled(cron = "0 0 1 * * *") // 매일 새벽 1시
    public void fetchAndSaveUserData() {
        try {
            String url = KEYCLOAK_URL + "/admin/realms/sso-test/users";
            RestTemplate restTemplate = new RestTemplate();

            String token = Keycloak.getAdminAccessToken();

            HttpHeaders requestHeader = new HttpHeaders();
            requestHeader.setContentType(MediaType.APPLICATION_JSON);
            requestHeader.set("Authorization", token);

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestHeader);
            Departments departments;
            ResponseEntity<List<Map>> response =
                    restTemplate.exchange(url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Map>>() {});
            List<Map> responseBody = getResponseBody(response);

            if (responseBody != null) {
                for (Map<String, Object> data : responseBody) {
                    Map<String, List<String>> attributes = (Map<String, List<String>>) data.get("attributes");
                    departments = null;
                    if (attributes != null) {
                        List<String> departmentList = attributes.get("department");
                        if (departmentList != null && !departmentList.isEmpty()) {
                            String departmentName = departmentList.get(0); // 첫 번째 department 값만 가져오기
                            departments = (departmentName != null) ? departmentsService.findByName(departmentName) : null;
                        }
                    }
                    url = KEYCLOAK_URL + "/admin/realms/sso-test/users/"+data.get("id")+"/groups";
                    ResponseEntity<List<Map>> groups =
                            restTemplate.exchange(url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Map>>() {});
                    List<Map> responseBody2 = getResponseBody(groups);
                    String auth = null;
                    if (responseBody2.size() == 1) {
                        auth = (String) responseBody2.get(0).get("name");
                    }

                    UserDto userDto = new UserDto((String) data.get("username"), (String) data.get("email"), departments, auth);
                    usersService.findByUsernameOrInsert(userDto);
                }
            }
            url = KEYCLOAK_URL + "/admin/realms/sso-test/users/"+ADMIN_ID+"/logout";
            restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Map>>() {});

        } catch (Exception e) {
            log.error("Exception : " + e);
            e.printStackTrace();
        }
    }
}
