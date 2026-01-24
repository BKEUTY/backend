package com.hcmut.bkuety.config;

import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CorsConfigurationSource corsConfigurationSource;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource))
                // Tắt CSRF để có thể gọi API từ Postman dễ dàng hơn
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        // Cho phép tất cả mọi người truy cập vào các đường dẫn bắt đầu bằng /api/
                        .requestMatchers("/api/product/**","/api/cart/**", "/api/order/**","/**").permitAll()
                        // Các đường dẫn khác vẫn yêu cầu đăng nhập (nếu muốn)
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
