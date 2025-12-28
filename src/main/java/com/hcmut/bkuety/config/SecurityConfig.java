package com.hcmut.bkuety.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Tắt CSRF để có thể gọi API từ Postman dễ dàng hơn
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        // Cho phép tất cả mọi người truy cập vào các đường dẫn bắt đầu bằng /api/
                        .requestMatchers("/api/product/**","/api/cart/**", "/api/order/**").permitAll()
                        // Các đường dẫn khác vẫn yêu cầu đăng nhập (nếu muốn)
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
