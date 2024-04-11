package com.aditya.xStay.service;

import com.aditya.xStay.dto.AuthRequest;
import com.aditya.xStay.dto.AuthResponse;
import com.aditya.xStay.dto.RegisterRequest;
import com.aditya.xStay.model.Role;
import com.aditya.xStay.model.User;
import com.aditya.xStay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*
 * @author adityagupta
 * @date 11/04/24
 */
@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JWTService jwtService;

    public AuthResponse register(RegisterRequest request){
        if(request.getRole() == null){
            request.setRole(Role.CUSTOMER);
        }
        User user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        String jwtToken = jwtService.generateToken(user);
        userRepository.save(user);
        return AuthResponse.builder().accessToken(jwtToken).build();
    }

    public AuthResponse login(AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userRepository.findByEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder().accessToken(jwtToken).build();
    }
}
