package kr.co.direa.office.config;

import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

//@Configuration
//public class WebConfig implements WebMvcConfigurer {
public class WebConfig {

    public CorsConfigurationSource corsConfiguration() {
        return request -> {
            org.springframework.web.cors.CorsConfiguration config =
                    new org.springframework.web.cors.CorsConfiguration();
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowedOriginPatterns(Collections.singletonList("*"));
            config.setAllowCredentials(true);
            return config;
        };
    }
}
