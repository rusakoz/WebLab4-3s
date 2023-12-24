package org.lab4.wed.weblab4.controller;

import org.lab4.wed.weblab4.db.dto.UserCreateEditDto;
import org.lab4.wed.weblab4.db.dto.UserReadDto;
import org.lab4.wed.weblab4.db.service.AuthJwtService;
import org.lab4.wed.weblab4.db.service.UserService;
import org.lab4.wed.weblab4.jwt.JwtAuthentication;
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

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final AuthJwtService authJwtService;
    private final UserService userService;

    @GetMapping("/{id}")
    public UserReadDto login(@PathVariable("id") Long id){
        Optional<UserReadDto> userReadDto = userService.findById(id);

        return userReadDto.orElseThrow();
    }

    @PostMapping(value = "/reg", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registration(@RequestBody UserCreateEditDto userDto) {
        if (userService.existsByName(userDto.name())) {
            return new ResponseEntity<>("Имя пользователя уже занято", HttpStatus.BAD_REQUEST);
        }
        
        userService.create(userDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "Bearer Authorization")
    @GetMapping("hello")
    public ResponseEntity<String> helloUser() {
        final JwtAuthentication authInfo = authJwtService.getAuthInfo();
        return ResponseEntity.ok("Hello user " + authInfo.getPrincipal() + "!");
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody JwtRequest authRequest) {
        JwtResponse token;
        try {
            token = authJwtService.login(authRequest);
        } catch (AuthException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(token);
    }

    @SecurityRequirement(name = "Bearer Authorization")
    @PostMapping("token")
    public ResponseEntity<?> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        JwtResponse token;
        try {
            token = authJwtService.getAccessToken(request.getRefreshToken());
        } catch (AuthException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(token);
    }

    @SecurityRequirement(name = "Bearer Authorization")
    @PostMapping("refresh")
    public ResponseEntity<?> getNewRefreshToken(@RequestBody RefreshJwtRequest request) {
        JwtResponse token;
        try {
            token = authJwtService.refresh(request.getRefreshToken());
        } catch (AuthException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(token);
    }

}
