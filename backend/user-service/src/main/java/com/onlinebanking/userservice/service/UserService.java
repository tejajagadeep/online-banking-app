package com.onlinebanking.userservice.service;

import com.onlinebanking.userservice.dto.UserDto;
import com.onlinebanking.userservice.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserDto updateUserDetails(String username, UserDto userDto);

    UserDto createUser(User user);

    Object getUserDetails(String username);
}
