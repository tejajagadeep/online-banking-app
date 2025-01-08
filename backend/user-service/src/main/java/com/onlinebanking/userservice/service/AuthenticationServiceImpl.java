package com.onlinebanking.userservice.service;

import com.onlinebanking.userservice.dto.AuthenticationResponseDto;
import com.onlinebanking.userservice.dto.RegisterUserDto;
import com.onlinebanking.userservice.dto.TokenType;
import com.onlinebanking.userservice.jwtconfig.JwtTokenGenerator;
import com.onlinebanking.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponseDto getJwtTokensAfterAuthentication(Authentication authentication) {
        try
        {
            var user = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(()->{
                        log.error("[AuthenticationServiceImpl:userSignInAuth] User :{} not found",authentication.getName());
                        return new UsernameNotFoundException("USER NOT FOUND ");});


            String accessToken = jwtTokenGenerator.generateAccessToken(authentication);

            log.info("[AuthenticationServiceImpl:userSignInAuth] Access token for user:{}, has been generated",user.getUsername());
            return  AuthenticationResponseDto.builder()
                    .accessToken(accessToken)
                    .accessTokenExpiresIn(15 * 60)
                    .username(user.getUsername())
                    .tokenType(TokenType.BEARER)
                    .build();


        }catch (Exception e){
            log.error("[AuthenticationServiceImpl:userSignInAuth]Exception while authenticating the user due to :{}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Please Try Again");
        }
    }

    @Override
    public AuthenticationResponseDto authenticateClient(RegisterUserDto requestDto) {
        // Perform authentication using the provided username and password
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT tokens after successful authentication
        return getJwtTokensAfterAuthentication(authentication);
    }
}

