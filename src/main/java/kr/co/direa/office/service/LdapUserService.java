package kr.co.direa.office.service;

import kr.co.direa.office.domain.LdapUser;
import kr.co.direa.office.dto.LdapUserDto;
import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import kr.co.direa.office.repository.LdapUserRepository;
import kr.co.direa.office.util.Keycloak;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.naming.Name;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static kr.co.direa.office.constant.Constants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class LdapUserService {
    private final LdapUserRepository ldapUserRepository;
    private final LdapTemplate ldapTemplate;
    private final EmailService emailService;
    @Value("${constants.admin-pw}") private String adminPw;
    @Value("${constants.admin}") private String admin;
    @Value("${constants.keycloak-url}") private String keycloakUrl;
    @Value("${constants.realm}") private String realm;

    public void addUser(LdapUserDto requestDto) {
        try {
            Name dn = buildDn(requestDto.getCn());

            LdapUser user = new LdapUser(
                    dn, requestDto.getCn(), requestDto.getSn(),
                    requestDto.getGidNumber(), requestDto.getUidNumber(), requestDto.getMail(),
                    requestDto.getUserPassword(), requestDto.getHomeDirectory(),
                    requestDto.getOu(), requestDto.getUid()
            );
            ldapTemplate.bind(dn, null, buildAttributes(user));
        } catch (NameNotFoundException e) {
            throw new CustomException(CustomErrorCode.NOT_FOUND_LDAP_DN);
        } catch (Exception e) {
            throw new RuntimeException("LDAP 오류: 객체 추가 중 문제가 발생하였습니다.", e);
        }
    }

    public List<LdapUserDto> getAllUsers() {
        List<LdapUser> LdapUserList = ldapUserRepository.findAll();
        return LdapUserList.stream()
                .map(LdapUserDto::new)
                .collect(Collectors.toList());
    }

    public void deleteUser(String cn) {
        LdapUser user = ldapUserRepository.findByCn(cn);
        if (user != null) {
            ldapUserRepository.delete(user);
        } else {
            throw new CustomException(CustomErrorCode.NOT_FOUND_LDAP_USER);
        }
    }

    protected Name buildDn(String cn) {
        return LdapNameBuilder.newInstance()
                .add("cn", "Users")
                .add("cn", cn)
                .build();
    }

    private Attributes buildAttributes(LdapUser user) {
        Attributes attrs = new BasicAttributes();
        BasicAttribute ocattr = new BasicAttribute("objectclass");
        ocattr.add("top");
        ocattr.add("inetOrgPerson");
        ocattr.add("posixAccount");
        attrs.put(ocattr);

        attrs.put(LDAP_CN_ATTR, user.getCn());
        attrs.put(LDAP_SN_ATTR, user.getSn());
        attrs.put(LDAP_UID_ATTR, user.getUid());
        attrs.put(LDAP_GID_NUMBER_ATTR, String.valueOf(user.getGidNumber()));
        attrs.put(LDAP_UID_NUMBER_ATTR, String.valueOf(user.getUidNumber()));
        attrs.put(LDAP_EMAIL_ATTR, user.getMail());
        attrs.put(LDAP_PASSWORD_ATTR, user.getUserPassword());
        attrs.put(LDAP_HOMEDIR_ATTR, "/home/" + user.getCn());
        attrs.put(LDAP_OU_ATTR, user.getOu());

        return attrs;
    }

    public LdapUser findByUidNumber(String uidNumber) {
        LdapUser user = ldapUserRepository.findByUidNumber(uidNumber);
        if (user != null) {
            return user;
        } else {
            throw new CustomException(CustomErrorCode.NOT_FOUND_LDAP_USER);
        }
    }

    public LdapUser findByCn(String cn) {
        LdapUser user = ldapUserRepository.findByCn(cn);
        if (user != null) {
            return user;
        } else {
            throw new CustomException(CustomErrorCode.NOT_FOUND_LDAP_USER);
        }
    }

    private List<Map> getResponseBody(ResponseEntity<List<Map>> response) {
        if (response != null && response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    public String reissuePassword(String cn) { // keycloak 비밀번호 재발행
        RestTemplate restTemplate = new RestTemplate();
        String tmpPassword = generateRandomPassword();
        try {
            String url = keycloakUrl + "/admin/realms/"+realm+"/users?email="+cn+"@"+DIREA_DOMAIN;

            String token = Keycloak.getAdminAccessToken(keycloakUrl, admin, realm, adminPw);

            HttpHeaders requestHeader = new HttpHeaders();
            requestHeader.setContentType(MediaType.APPLICATION_JSON);
            requestHeader.set("Authorization", token);

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestHeader);
            ResponseEntity<List<Map>> response =
                    restTemplate.exchange(url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Map>>() {});
            List<Map> responseBody = getResponseBody(response);

            Map<String, Object> userMap = new HashMap<>();
            userMap.put("type", "password");
            userMap.put("value", tmpPassword);
            userMap.put("temporary", true);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(userMap, requestHeader);
            url = keycloakUrl + "/admin/realms/"+realm+"/users/"+responseBody.get(0).get("id")+"/reset-password";
            log.info(url);
            restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

            String to = cn + "@"+DIREA_DOMAIN;
            String subject = "임시 패스워드 발행";
            String text = "Your new temporary password is: " + tmpPassword;
            emailService.sendSimpleMessage(to, subject, text);
        } catch (Exception e) {
            throw new CustomException(CustomErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return tmpPassword;
    }

    private String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[12];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
