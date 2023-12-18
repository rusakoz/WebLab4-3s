package org.lab4.wed.weblab4.controller;

import org.lab4.wed.weblab4.db.entity.Users;
import org.lab4.wed.weblab4.db.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("test")
public class UserRestController {
    private final UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("log")
    @ResponseBody
    public List<Users> test(){
        return userRepository.findAll();
    }

    @PostMapping("log2")
    @ResponseBody
    public String add(){
        userRepository.save(Users.builder().id(23L).name("Rus").password("pass").dateOfCreation(LocalDate.now()).build());
        return "dasdas";
    }

}
