package com.aditya.xStay.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author adityagupta
 * @date 11/04/24
 */
@RestController
@RequestMapping("/welcome")
public class WelcomeController {
    @GetMapping("")
    public String welcome(){
        return "Hello you are authenticated with jwt";
    }
}
