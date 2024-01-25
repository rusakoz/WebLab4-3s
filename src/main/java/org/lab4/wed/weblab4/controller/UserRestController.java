package org.lab4.wed.weblab4.controller;

import java.util.HashMap;
import java.util.Map;

import org.lab4.wed.weblab4.db.dto.UserCreateEditDto;
import org.lab4.wed.weblab4.db.service.AuthJwtService;
import org.lab4.wed.weblab4.db.service.UserService;
import org.lab4.wed.weblab4.jwt.JwtRequest;
import org.lab4.wed.weblab4.jwt.JwtResponse;
import org.lab4.wed.weblab4.jwt.RefreshJwtRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final AuthJwtService authJwtService;
    private final UserService userService;

    @PostMapping(value = "/reg", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registration(@RequestBody UserCreateEditDto userDto) {
        Map<String, String> message = new HashMap<>();
        if (userService.existsByName(userDto.getName())) {
            message.put("error", "Имя занято");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        
        userService.registration(userDto);
        
        message.put("status", "Created");
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody JwtRequest authRequest) {
        JwtResponse token;
        try {
            token = authJwtService.login(authRequest);
        } catch (AuthException e) {
            Map<String, String> message = new HashMap<>();
            message.put("error", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return ResponseEntity.ok(token);
    }

    @PostMapping(value = "token")
    public ResponseEntity<?> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        JwtResponse token;
        try {
            token = authJwtService.getAccessToken(request.getRefreshToken());
        } catch (AuthException e) {
            Map<String, String> message = new HashMap<>();
            message.put("error", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return ResponseEntity.ok(token);
    }

    @SecurityRequirement(name = "Bearer Authorization")
    @PostMapping(value = "refresh", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNewRefreshToken(@RequestBody RefreshJwtRequest request) {
        JwtResponse tokens;
        try {
            tokens = authJwtService.refresh(request.getRefreshToken());
        } catch (AuthException e) {
            Map<String, String> message = new HashMap<>();
            message.put("error", e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return ResponseEntity.ok(tokens);
    }

}
