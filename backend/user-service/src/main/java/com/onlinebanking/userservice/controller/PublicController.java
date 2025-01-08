package com.onlinebanking.userservice.controller;

import com.onlinebanking.userservice.dto.RegisterUserDto;
import com.onlinebanking.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1.0/public")
public class PublicController {

    private final UserService userService;

    @PostMapping("/register")
    ResponseEntity<Object> createUser(@Valid @RequestBody RegisterUserDto registerUserDto){
        return new ResponseEntity<>(userService.createUser(registerUserDto), HttpStatus.CREATED);
    }
}
