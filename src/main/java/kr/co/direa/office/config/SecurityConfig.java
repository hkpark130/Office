package kr.co.direa.office.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class SecurityConfig {
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.oauth2Client(Customizer.withDefaults());
        http.oauth2Login(it -> it.tokenEndpoint(Customizer.withDefaults())
                .userInfoEndpoint(Customizer.withDefaults())
        );

        http.cors(Customizer.withDefaults());
//        http.cors(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth ->
                        auth.requestMatchers(
//                                HttpMethod.GET, "/oauth2/**", "/login", "/login/oauth2/**", "/api/health", "/").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/api/test", "/test/admin/**").hasAuthority("Admin")
//                                .requestMatchers(HttpMethod.POST, "/logout").authenticated()
//                                .requestMatchers(HttpMethod.GET, "/api/**").authenticated()
//                                .anyRequest().authenticated()
                                HttpMethod.GET, "/**").permitAll()
        );

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        http.logout(logout -> logout.logoutSuccessHandler(logoutSuccessHandler()));


        return http.build();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler = new OidcClientInitiatedLogoutSuccessHandler(
                this.clientRegistrationRepository);
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("/");

        return oidcLogoutSuccessHandler;
    }

    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities) -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            authorities.forEach(authority -> {
                if (OidcUserAuthority.class.isInstance(authority)) {
                    OidcUserAuthority oidcUserAuthority = (OidcUserAuthority)authority;

                    OidcIdToken idToken = oidcUserAuthority.getIdToken();
                    OidcUserInfo userInfo = oidcUserAuthority.getUserInfo();

                    List<String> group = userInfo.getClaim("groups");
                    group.forEach(it -> mappedAuthorities.add(new SimpleGrantedAuthority(it.replace("/", ""))));
                }
            });

            return mappedAuthorities;
        };
    }
}
