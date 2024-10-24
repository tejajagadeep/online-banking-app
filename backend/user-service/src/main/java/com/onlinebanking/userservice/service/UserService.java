package com.onlinebanking.userservice.service;

import com.onlinebanking.userservice.dto.RegisterUserDto;
import com.onlinebanking.userservice.dto.UserDto;
import com.onlinebanking.userservice.dto.UserPasswordDto;

public interface UserService {

    UserDto createUser(RegisterUserDto registerUserDto);

    UserDto changePassword(UserPasswordDto userPasswordDto);
}
