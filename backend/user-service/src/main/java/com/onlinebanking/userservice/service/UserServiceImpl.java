package com.onlinebanking.userservice.service;

import com.onlinebanking.userservice.dto.RegisterUserDto;
import com.onlinebanking.userservice.dto.UserDto;
import com.onlinebanking.userservice.dto.UserPasswordDto;
import com.onlinebanking.userservice.exception.MatchingPasswordException;
import com.onlinebanking.userservice.model.User;
import com.onlinebanking.userservice.exception.IncorrectPasswordException;
import com.onlinebanking.userservice.exception.UserNotFoundException;
import com.onlinebanking.userservice.exception.UsernameAlreadyExistsException;
import com.onlinebanking.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(RegisterUserDto registerUserDto) {

        userRepository.findByUsername(registerUserDto.getUsername())
                .ifPresent(user1 -> {
                    log.info("User already Exist : "+ registerUserDto.getUsername());
                    throw new UsernameAlreadyExistsException(registerUserDto.getUsername()+" Username already exists");
                });

        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registerUserDto.getPassword()));

        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto changePassword(UserPasswordDto userPasswordDto) {

        User user = userRepository.findByUsername(userPasswordDto.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + userPasswordDto.getOldPassword()));

        if (userPasswordDto.getOldPassword().equals(userPasswordDto.getNewPassword())) {
            throw new MatchingPasswordException("Old password and new password are same");
        }

        if (bCryptPasswordEncoder.matches(userPasswordDto.getOldPassword(), user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(userPasswordDto.getNewPassword()));
            return modelMapper.map(userRepository.save(user), UserDto.class);
        } else {
            throw new IncorrectPasswordException("Incorrect password");
        }

    }

}
