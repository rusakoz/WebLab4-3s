package org.lab4.wed.weblab4.config;

import java.util.Arrays;

import org.lab4.wed.weblab4.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private static final String[] ORDER_1_SECURITY_WHITE_LIST = {
        "/", 
        "/assets/**", "/js/**", "/images/**", "/error**", "/favicon**",
        "/v3/api-docs/**", "/swagger-ui/**", 
        "/user/login", "/user/token", "/user/reg"
    };

    private static final String[] ORDER_1_AUTHORIZE_WHITE_LIST = {
        "/", 
        "/assets/**", "/js/**", "/images/**", "/error**", "/favicon**",
        "/v3/api-docs/**", "/swagger-ui/**", 
        "/user/login", "/user/token", "/user/reg"
    };

    private static final String[] ORDER_2_SECURITY_WHITE_LIST = {
        "/**"
    };

    @Bean
    public StrictHttpFirewall httpFirewall() {
    StrictHttpFirewall firewall = new StrictHttpFirewall();
    firewall.setAllowedHttpMethods(Arrays.asList("GET", "POST"));
    return firewall;
}
    
    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChainOne(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(CsrfConfigurer::disable).securityMatcher(ORDER_1_SECURITY_WHITE_LIST)
                .authorizeHttpRequests(auth -> {
                    auth
                        .requestMatchers(ORDER_1_AUTHORIZE_WHITE_LIST).permitAll()
                        .anyRequest().authenticated();

                    }).formLogin(formLogin -> {
                        formLogin
                            .loginPage("/")
                            .defaultSuccessUrl("/loginsuccess")
                            .permitAll();
                    });
                        
        return httpSecurity.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChainTwo(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(CsrfConfigurer::disable).securityMatcher(ORDER_2_SECURITY_WHITE_LIST)
                .authorizeHttpRequests(auth -> {
                        auth.anyRequest().authenticated();
                    })
                    .addFilterAfter(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
                        
        return httpSecurity.build();
    }
}
