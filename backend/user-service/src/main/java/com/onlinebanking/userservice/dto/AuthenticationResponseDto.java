package com.onlinebanking.userservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponseDto {

    private String accessToken;
    private int accessTokenExpiresIn;
    private TokenType tokenType;
    private String username;
}
