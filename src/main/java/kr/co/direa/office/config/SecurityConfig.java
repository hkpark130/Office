package kr.co.direa.office.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    private
    CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowedOriginPatterns(Collections.singletonList("*"));
            config.setAllowCredentials(true);
            return config;
        };
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.oauth2Client(Customizer.withDefaults());
        http.oauth2Login(it -> it.tokenEndpoint(Customizer.withDefaults())
                .userInfoEndpoint(Customizer.withDefaults())
        );

        http.cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()));
        http.cors(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth ->
                        auth.requestMatchers(
                                        new AntPathRequestMatcher("/login/**")
                                        , new AntPathRequestMatcher("/api/health")
                                        , new AntPathRequestMatcher("/oauth2/**")
                                        , new AntPathRequestMatcher("/logout/**")
                                        , new AntPathRequestMatcher("/h2-console/**")
                                ).permitAll()
                                .requestMatchers(
                                        new AntPathRequestMatcher("/api/test")
                                        , new AntPathRequestMatcher("/test/admin/**")
                                ).hasAuthority("Admin")
                                .anyRequest().authenticated())
//                                .anyRequest().permitAll()) // 개발용
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(a -> a.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()));

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
        http.logout(logout -> logout.logoutSuccessHandler(logoutSuccessHandler()));
        http.oauth2Login(
                oauth2 -> oauth2.
                        successHandler(loginSuccessHandler()).
                        userInfoEndpoint(userInfo -> userInfo
                                .userAuthoritiesMapper(userAuthoritiesMapper()))

        );

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
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
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
