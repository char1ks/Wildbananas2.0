package com.example.SpringRedisPet_20december.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable() // Отключаем CSRF (для простоты, в продакшене используйте CSRF)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/visitor/login", "/visitor/register","/visitor/api/save","/process_login", "/oauth2/**").permitAll() // Разрешаем доступ к этим URL
                        .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                )
                .formLogin(form -> form
                        .loginPage("/visitor/login") // Указываем страницу входа
                        .loginProcessingUrl("/process_login") // URL для обработки входа
                        .defaultSuccessUrl("/product/main", true) // Перенаправление после успешного входа через форму
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/product/main", true) // Перенаправление после успешного входа через OAuth2
                )
                .logout(logout -> logout
                        .logoutUrl("/perform_logout") // URL для выхода
                        .logoutSuccessUrl("/visitor/login") // Перенаправление после выхода
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}