package com.onlinebanking.userservice.controller;

import com.onlinebanking.userservice.jwtconfig.AuthService;
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

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<Object> authenticateUser(Authentication authentication){
        return ResponseEntity.ok(authService.getJwtTokensAfterAuthentication(authentication).getAccessToken());
    }
}
