package com.onlinebanking.userservice.service;

import com.onlinebanking.userservice.dto.AuthenticationResponseDto;
import com.onlinebanking.userservice.dto.RegisterUserDto;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    public AuthenticationResponseDto getJwtTokensAfterAuthentication(Authentication authentication);
    AuthenticationResponseDto authenticateClient(RegisterUserDto requestDto);
}
