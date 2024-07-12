package kr.co.direa.office.controller;

import kr.co.direa.office.dto.UserDto;
import kr.co.direa.office.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {
    private final UsersService usersService;

    @GetMapping(value = "/user")
    ResponseEntity<?> getUser(Principal principal) {
        OAuth2User user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(
                user
        );

//    ResponseEntity<?> getUser() throws IOException {
//        Path filePath = Path.of(System.getProperty("user.dir") + "/user.json");
//        String jsonContent = Files.readString(filePath);
//
//        return ResponseEntity.ok(
//                jsonContent
//        );
    }

    @GetMapping(value = "/admin")
    ResponseEntity<?> getAdmin() {
        List<UserDto> userDtoList = usersService.getAdminList();

        return ResponseEntity.ok(
                userDtoList
        );
    }
}
