package com.aditya.xStay.config;
/*
 * @author adityagupta
 * @date 11/04/24
 */

import com.aditya.xStay.service.JWTService;
import com.aditya.xStay.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JWTService jwtService;
    @Autowired
    UserService userService;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userName;

        //check authentication header
        if(authHeader==null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }

        //extract the token from the header
        jwt = authHeader.substring(7);

        //extract the username from the token(username is email)
        userName = jwtService.extractUserName(jwt);

        //authenticate is not already authenticated
        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails user = userService.loadUserByUsername(userName);
            // check if the token is valid
            if(jwtService.isTokenValid(jwt, user)){
                // update the security context holder
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities()
                );
                // set additional details such as user's ipaddress, browser ot other attributes
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        //call the next filter
        filterChain.doFilter(request, response);
    }
}
