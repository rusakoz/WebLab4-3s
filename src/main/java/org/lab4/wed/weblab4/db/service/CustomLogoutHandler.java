package org.lab4.wed.weblab4.db.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler{

    private final AuthJwtService authJwtService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        

        authJwtService.removeMapValueByName(authJwtService.getAuthInfo().getName());
    }
}
