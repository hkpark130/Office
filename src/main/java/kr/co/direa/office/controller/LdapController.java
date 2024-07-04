package kr.co.direa.office.controller;

import kr.co.direa.office.dto.LdapUserDto;
import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import kr.co.direa.office.service.LdapUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kr.co.direa.office.constant.Constants.SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LdapController {
    private final LdapUserService ldapUserService;

    @GetMapping(value = "/ldap-users")
    ResponseEntity<?> getAllUsers() {
        List<LdapUserDto> ldapUserDtoList = ldapUserService.getAllUsers();

        return ResponseEntity.ok(
                ldapUserDtoList
        );
    }

    @PostMapping(value = "/ldap-user")
    ResponseEntity<?> addUser(@RequestBody LdapUserDto requestDto) {
        ldapUserService.addUser(requestDto);

        return ResponseEntity.ok(
                SUCCESS
        );
    }

    @DeleteMapping(value = "/ldap-user/{cn}")
    ResponseEntity<?> deleteUser(@PathVariable String cn) {
        ldapUserService.deleteUser(cn);

        return ResponseEntity.ok(
                SUCCESS
        );
    }

    @GetMapping(value = "/check-ldap-user-uidnum/{uidNumber}")
    ResponseEntity<?> checkUidNumberDup(@PathVariable String uidNumber) {
        try {
            ldapUserService.findByUidNumber(uidNumber);
            return ResponseEntity.ok(
                    false
            );
        } catch (CustomException e) {
            if (e.getErrorCode() == CustomErrorCode.NOT_FOUND_LDAP_USER) {
                return ResponseEntity.ok(true);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping(value = "/reissue-password/{cn}")
    ResponseEntity<?> reissuePassword(@PathVariable String cn) {
        String tmpPassword = ldapUserService.reissuePassword(cn);

        return ResponseEntity.ok(
                tmpPassword
        );
    }

    @GetMapping(value = "/check-ldap-user-cn/{cn}")
    ResponseEntity<?> checkCnDup(@PathVariable String cn) {
        try {
            ldapUserService.findByCn(cn);
            return ResponseEntity.ok(
                    false
            );
        } catch (CustomException e) {
            if (e.getErrorCode() == CustomErrorCode.NOT_FOUND_LDAP_USER) {
                return ResponseEntity.ok(true);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

}
