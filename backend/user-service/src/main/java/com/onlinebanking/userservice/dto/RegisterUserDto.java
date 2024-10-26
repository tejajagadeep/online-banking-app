package com.onlinebanking.userservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {

    @NotEmpty(message = "username is required field")
    @Size(min = 5, max = 15, message = "username should be between 5 and 10 characters")
    private String username;

    @NotEmpty(message = "Password is required field")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@^$#!%*?&()`~+=_-])[A-Za-z\\d@^$#!%*?&()`~+=_-]{8,}$",
            message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character"
    )
    @Size(max = 20, message = "Password should be less than 20 characters")
    private String password;
}
