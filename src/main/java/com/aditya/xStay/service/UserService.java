package com.aditya.xStay.service;
/*
 * @author adityagupta
 * @date 11/04/24
 */

import com.aditya.xStay.model.User;
import com.aditya.xStay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public Optional<User> getUserById(Integer userId){
        return userRepository.findById(userId);
    }

}
