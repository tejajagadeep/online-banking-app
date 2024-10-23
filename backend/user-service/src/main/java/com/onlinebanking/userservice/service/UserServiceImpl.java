package com.onlinebanking.userservice.service;

import com.onlinebanking.userservice.dto.UserDetailsDto;
import com.onlinebanking.userservice.dto.UserDto;
import com.onlinebanking.userservice.entity.User;
import com.onlinebanking.userservice.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    public UserDto updateUserDetails(String username, UserDto userDto) {
        return null;
    }

    @Override
    public UserDto createUser(User user) {
        userRepository.save(user);
        log.info(user.toString());
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public Object getUserDetails(String username) {
        return userRepository.findByUsername(username);
    }

}
