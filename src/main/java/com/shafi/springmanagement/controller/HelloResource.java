package com.shafi.springmanagement.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloResource {

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome shafi";
    }

}
