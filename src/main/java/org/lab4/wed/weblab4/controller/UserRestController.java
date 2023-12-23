package org.lab4.wed.weblab4.controller;

import org.lab4.wed.weblab4.db.dto.UserCreateEditDto;
import org.lab4.wed.weblab4.db.dto.UserReadDto;
import org.lab4.wed.weblab4.db.entity.Users;
import org.lab4.wed.weblab4.db.repository.UserRepository;
import org.lab4.wed.weblab4.db.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

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

    @PostMapping("log2")
    public String add(){
        //userService.create(Users.builder().id(23L).name("Rus").password("pass").dateOfCreation(LocalDate.now()).build());
        return "dasdas";
    }

}
