package org.lab4.wed.weblab4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/reg")
public class RegistrationController {
    @GetMapping
    public String registration(){
        return "Reggg";
    }
}
