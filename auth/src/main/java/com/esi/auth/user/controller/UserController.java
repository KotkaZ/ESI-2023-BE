package com.esi.auth.user.controller;

import com.esi.auth.jwt.JwtService;
import com.esi.auth.user.dto.UserDto;
import com.esi.auth.user.model.User;
import com.esi.auth.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
@Slf4j
@CrossOrigin(origins = {"http://localhost:9090/",  "http://localhost:8080/"})
//@CrossOrigin(origins = {"http://localhost:9090/"})
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String logInAndGetToken(@RequestBody UserDto userDto) {

        if(userDto.getName() == null || userDto.getPassword() == null) {
            throw new UsernameNotFoundException("UserName or Password is Empty");
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getName(), userDto.getPassword()));
        // If the user is authenticated we generate the token, otherwise, we throw an exception
        //log.info("authentication.isAuthenticated()  {} ", authentication);

        if (authentication.isAuthenticated()) {
            log.info("jwtService.generateToken(authRequest.getName())  {} ", jwtService.generateToken(userDto.getName()).toString());
            return jwtService.generateToken(userDto.getName());
        } else {
            throw new UsernameNotFoundException("The user cannot be authinticated!");
        }
    }

    @GetMapping("/authenticate")
    public Boolean authenticate(@RequestHeader("Authorization") String header) {
        String token = header.replace("Bearer ", "");
        log.info(" authenticate - token {} ", token);
        return  jwtService.validateToken(token);
    }

    @PostMapping("/signup")
    public String signupUser(@RequestBody User user){
        userService.addUser(user);
        String jwtToken = jwtService.generateToken(user.getName());
        return jwtToken;
    }
}
