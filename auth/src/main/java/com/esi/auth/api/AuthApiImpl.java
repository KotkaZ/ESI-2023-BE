package com.esi.auth.api;

import com.esi.auth.jwt.JwtService;
import com.esi.auth.user.mapper.UserMapper;
import com.esi.auth.user.service.UserService;
import com.esi.bookings.AuthApi;
import com.esi.bookings.models.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthApiImpl implements AuthApi {

    private final UserMapper userMapper;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<Boolean> authenticate(String authorization) {
        String token = authorization.replace("Bearer ", "");
        log.info(" authenticate - token {} ", token);
        return ResponseEntity.ok(jwtService.validateToken(token));
    }

    @Override
    public ResponseEntity<String> loginAndGetToken(UserDto userDto) {
        if (userDto.getName() == null || userDto.getPassword() == null) {
            throw new UsernameNotFoundException("UserName or Password is Empty");
        }

        val authenticationToken = new UsernamePasswordAuthenticationToken(
            userDto.getName(), userDto.getPassword());
        val authentication = authenticationManager.authenticate(authenticationToken);

        if (authentication.isAuthenticated()) {
            val jwt = jwtService.generateToken(userDto.getName());
            log.info("jwtService.generateToken(authRequest.getName())  {} ", jwt);
            return ResponseEntity.ok(jwt);
        }
        throw new UsernameNotFoundException("The user cannot be authenticated!");
    }

    @Override
    public ResponseEntity<String> signupUser(UserDto userDto) {
        val user = userMapper.dtoToEntity(userDto);
        userService.addUser(user);
        return ResponseEntity.ok(jwtService.generateToken(userDto.getName()));
    }

}
