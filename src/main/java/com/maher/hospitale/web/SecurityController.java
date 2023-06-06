package com.maher.hospitale.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/notAutorized")
    public String notAuthorized() {
        return "notAutorized" ;
    }
    @GetMapping("/login")
    public String login() {
        return "login" ;
    }
}
