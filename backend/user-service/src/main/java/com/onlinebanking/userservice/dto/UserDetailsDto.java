package com.onlinebanking.userservice.dto;

import java.time.LocalDateTime;

public class UserDetailsDto {
    private long userId;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
