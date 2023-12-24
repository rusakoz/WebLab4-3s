package org.lab4.wed.weblab4.config;

import org.lab4.wed.weblab4.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/assets/**","/js/**", "/images/**", "/error**",
                                                        "/v3/api-docs/**", "/swagger-ui/**",
                                                        "/user/login", "/user/token").permitAll()
                        .anyRequest().authenticated())
                        .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(login -> login
                        .loginPage("/")
                        .defaultSuccessUrl("/loginsuccess")
                        .permitAll());
        return httpSecurity.build();
    }
}
