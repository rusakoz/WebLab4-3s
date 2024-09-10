package org.lab4.wed.weblab4.config;

import java.util.Arrays;

import org.lab4.wed.weblab4.db.service.CustomLogoutHandler;
import org.lab4.wed.weblab4.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final CustomLogoutHandler logoutHandler;
    private static final String[] ORDER_1_SECURITY_WHITE_LIST = {
        "/", 
        "/assets/**", "/js/**", "/images/**", "/error**", "/favicon**",
        "/v3/api-docs/**", "/swagger-ui/**", 
        "/user/login", "/user/token", "/user/reg", "/logout"
    };

    private static final String[] ORDER_1_AUTHORIZE_WHITE_LIST = {
        "/", 
        "/assets/**", "/js/**", "/images/**", "/error**", "/favicon**",
        "/v3/api-docs/**", "/swagger-ui/**", 
        "/user/login", "/user/token", "/user/reg"
    };

    private static final String[] ORDER_2_SECURITY_CHECK_LIST = {
        "/result/get", "/result/save", "/user/refresh"
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
        // HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(Directive.ALL));
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
                    })
                    .logout(httpSecurityLogoutConfigurer ->
                    httpSecurityLogoutConfigurer
                    .logoutUrl("/logout")
                    .addLogoutHandler(logoutHandler)
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .permitAll()
                    );
                        
        return httpSecurity.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChainTwo(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(CsrfConfigurer::disable).securityMatcher(ORDER_2_SECURITY_CHECK_LIST)
                .authorizeHttpRequests(auth -> {
                        auth.anyRequest().authenticated();
                    })
                    .addFilterAfter(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
                        
        return httpSecurity.build();
    }
}
