package kr.co.direa.office.service;

import kr.co.direa.office.domain.Departments;
import kr.co.direa.office.domain.Users;
import kr.co.direa.office.dto.UserDto;
import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import kr.co.direa.office.repository.UsersRepository;
import kr.co.direa.office.util.Keycloak;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static kr.co.direa.office.constant.Constants.ADMIN_GROUP_NAME;
import static kr.co.direa.office.constant.Constants.DIREA_DOMAIN;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    @Value("${constants.admin-group-id}") private String adminGroupId;
    @Value("${constants.admin-pw}") private String adminPw;
    @Value("${constants.admin}") private String admin;
    @Value("${constants.keycloak-url}") private String keycloakUrl;
    @Value("${constants.realm}") private String realm;

    public Users save(UserDto userDto){
        return usersRepository.save(userDto.toEntity());
    }

    public Optional<Users> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public Users findByUsernameOrInsert(UserDto userDto) {
        Users user = usersRepository.findByUsername(userDto.getUsername()).orElse(null);
        if (user == null) {
            user = usersRepository.save(userDto.toEntity());
        } else {
            user.setAuth(userDto.getAuth());
            user = usersRepository.save(user);
        }
        return user;
    }

    private List<Map> getResponseBody(ResponseEntity<List<Map>> response) {
        if (response != null && response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    public Users getCurrentAdmin() {
        OAuth2User adminUser = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users adminObj = usersRepository.findByUsername(adminUser.getName())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER,
                        "해당 유저가 없습니다. username="+adminUser.getName()));
        return adminObj;
    }

    public List<UserDto> getAdminList() {
        List<UserDto> userDtoList = new ArrayList<UserDto>();
        RestTemplate restTemplate = new RestTemplate();
        try {
            String url = keycloakUrl + "/admin/realms/" + realm + "/groups/" + adminGroupId + "/members";
            String token = Keycloak.getAdminAccessToken(keycloakUrl, admin, realm, adminPw);

            HttpHeaders requestHeader = new HttpHeaders();
            requestHeader.setContentType(MediaType.APPLICATION_JSON);
            requestHeader.set("Authorization", token);

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestHeader);
            ResponseEntity<List<Map>> response =
                    restTemplate.exchange(url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Map>>() {});
            List<Map> responseBody = getResponseBody(response);

            if (responseBody != null) {
                for (Map<String, Object> data : responseBody) {
                    String username = (String) data.get("username");
                    Users user = findByUsername(username).orElse(null);
                    if (user != null) {
                        userDtoList.add(new UserDto(user));
                    }
                }
            }

        } catch (Exception e) {
            throw new CustomException(CustomErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return userDtoList;
    }

    public boolean isAdmin() {
        OAuth2User user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ArrayList<String> groups = user.getAttribute("groups");
        return groups != null && groups.contains("/"+ADMIN_GROUP_NAME);
    }
}
