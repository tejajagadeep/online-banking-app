package com.onlinebanking.userservice.jwtconfig;

import com.onlinebanking.userservice.dto.AuthenticationResponseDto;
import com.onlinebanking.userservice.dto.TokenType;
import com.onlinebanking.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenGenerator jwtTokenGenerator;
    public AuthenticationResponseDto getJwtTokensAfterAuthentication(Authentication authentication) {
        try
        {
            var user = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(()->{
                        log.error("[AuthService:userSignInAuth] User :{} not found",authentication.getName());
                        return new UsernameNotFoundException("USER NOT FOUND ");});


            String accessToken = jwtTokenGenerator.generateAccessToken(authentication);

            log.info("[AuthService:userSignInAuth] Access token for user:{}, has been generated",user.getUsername());
            return  AuthenticationResponseDto.builder()
                    .accessToken(accessToken)
                    .accessTokenExpiresIn(15 * 60)
                    .username(user.getUsername())
                    .tokenType(TokenType.BEARER)
                    .build();


        }catch (Exception e){
            log.error("[AuthService:userSignInAuth]Exception while authenticating the user due to :{}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Please Try Again");
        }
    }
}

