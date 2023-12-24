package org.lab4.wed.weblab4.controller;


import org.lab4.wed.weblab4.db.dto.UserCreateEditDto;
import org.lab4.wed.weblab4.db.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/reg")
@RequiredArgsConstructor
public class RegistrationController {
    
    private final UserService service;

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postMethodName(@RequestBody UserCreateEditDto userDto) {
        if (service.existsByName(userDto.name())) {
            return new ResponseEntity<>("Имя пользователя уже занято", HttpStatus.BAD_REQUEST);
        }
        
        service.create(userDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
