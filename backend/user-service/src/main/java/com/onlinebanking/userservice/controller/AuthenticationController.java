package com.onlinebanking.userservice.controller;

import com.onlinebanking.userservice.dto.RegisterUserDto;
import com.onlinebanking.userservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login-in")
    public ResponseEntity<Object> loginIn(Authentication authentication){
        return ResponseEntity.ok(authenticationService.getJwtTokensAfterAuthentication(authentication).getAccessToken());
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Object> signIn(RegisterUserDto registerUserDto){
        return ResponseEntity.ok(authenticationService.authenticateClient(registerUserDto).getAccessToken());
    }
}
