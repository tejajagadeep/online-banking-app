package com.onlinebanking.userservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordDto {

    @NotEmpty(message = "username is required field")
    @Size(min = 5, max = 15, message = "username is invalid")
    private String username;

    @NotEmpty(message = "Password is required field")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@^$#!%*?&()`~+=_-])[A-Za-z\\d@^$#!%*?&()`~+=_-]{8,}$",
            message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character"
    )
    private String oldPassword;

    @NotEmpty(message = "Password is required field")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@^$#!%*?&()`~+=_-])[A-Za-z\\d@^$#!%*?&()`~+=_-]{8,}$",
            message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character"
    )
    private String newPassword;
}
