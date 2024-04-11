package com.aditya.xStay.controller;

import com.aditya.xStay.dto.AuthRequest;
import com.aditya.xStay.dto.AuthResponse;
import com.aditya.xStay.dto.RegisterRequest;
import com.aditya.xStay.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author adityagupta
 * @date 11/04/24
 */
@RestController
@RequestMapping("")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> register(
            @RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authService.login(authRequest));
    }

}
