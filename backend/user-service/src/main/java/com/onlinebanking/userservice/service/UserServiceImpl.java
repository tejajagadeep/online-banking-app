package com.onlinebanking.userservice.service;

import com.onlinebanking.userservice.dto.UserDto;
import com.onlinebanking.userservice.entity.User;
import com.onlinebanking.userservice.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Object construct() {
        User userDto = new User();
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setUpdatedAt(LocalDateTime.now());
        userDto.setUsername("hello");
        userDto.setEmail("hello@gmail.com");
        List<User> users = new ArrayList<>();
        //50 - 1mb
        //550 - 800kb
        List<UserDto> userDtos = new ArrayList<>();
        return userDto;
    }

    @Override
    public UserDto updateUserDetails(String username, UserDto userDto) {
        return null;
    }

    @Override
    public UserDto createUser(User user) {
        userRepository.save(user);
        log.info(user.toString());
//        UserDto userDto = new UserDto();
//        userDto.setUsername(user.getUsername());
//        userDto.setEmail(user.getEmail());
//        userDto.setUpdatedAt(user.getUpdatedAt());
//        return userDto;
        return modelMapper.map(user, UserDto.class);
    }

}
