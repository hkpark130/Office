package kr.co.direa.office.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.direa.office.domain.Users;
import kr.co.direa.office.dto.UserDto;
import kr.co.direa.office.service.UsersService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UsersService usersService;

    public CustomAuthenticationSuccessHandler(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
//        if (authentication.getPrincipal() instanceof OAuth2User) {
//            OAuth2User userDetails = (OAuth2User) authentication.getPrincipal();
//            String username = userDetails.getAttribute("preferred_username");
//
//            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//            List<UserDto> userList = authorities.stream()
//                    .map(authority -> new UserDto(username, authority.getAuthority(), null))
//                    .collect(Collectors.toList());
//
//            for (UserDto userDto : userList) {
//                Optional<Users> existingUserOptional = usersService.findByUsername(username);
//                if (existingUserOptional.isPresent()) {
//                    Users existingUser = existingUserOptional.get();
//                    existingUser.setAuth(userDto.getAuth());
//                    usersService.save(new UserDto(existingUser));
//                } else {
//                    usersService.save(userDto);
//                }
//            }
//        }

        response.sendRedirect("/");
    }
}
