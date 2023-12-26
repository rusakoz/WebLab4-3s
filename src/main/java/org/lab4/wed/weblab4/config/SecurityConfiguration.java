package org.lab4.wed.weblab4.config;

import org.lab4.wed.weblab4.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private static final String[] ORDER1_WHITE_LIST = {
        "/", 
        "/assets/**", "/js/**", "/images/**", "/error**",
        "/v3/api-docs/**", "/swagger-ui/**", 
    };

    private static final String[] ORDER2_WHITE_LIST = {
        "/", 
        "/user/login", "/user/token"
    };
    
    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChainOne(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(CsrfConfigurer::disable).securityMatcher(ORDER1_WHITE_LIST)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(ORDER1_WHITE_LIST).permitAll();

                    })
                    ;
                        
        return httpSecurity.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChainTwo(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(CsrfConfigurer::disable).securityMatcher(ORDER2_WHITE_LIST)
                .authorizeHttpRequests(auth -> {
                        auth.requestMatchers(ORDER2_WHITE_LIST).permitAll()
                            .anyRequest().authenticated();
                    })
                    .addFilterAfter(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
                    .formLogin(formLogin -> {
                        formLogin
                            .loginPage("/")
                            .defaultSuccessUrl("/loginsuccess")
                            .permitAll();
                    });
                        
        return httpSecurity.build();
    }
}
