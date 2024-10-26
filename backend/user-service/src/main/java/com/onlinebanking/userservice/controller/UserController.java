package com.onlinebanking.userservice.controller;

import com.onlinebanking.userservice.dto.RegisterUserDto;
import com.onlinebanking.userservice.dto.UserPasswordDto;
import com.onlinebanking.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('SCOPE_CREATE_ACCOUNT')")
    public String hello() {
        return "Hello from User Service!";
    }

    @PostMapping("/register")
    ResponseEntity<Object> createUser(@Valid @RequestBody RegisterUserDto registerUserDto){
        return new ResponseEntity<>(userService.createUser(registerUserDto), HttpStatus.CREATED);
    }

    @PutMapping("/change-password")
    @PreAuthorize("hasAnyAuthority('CREATE_ACCOUNT')")
    public ResponseEntity<Object> changePassword(@Valid @RequestBody UserPasswordDto userPasswordDto){
        return ResponseEntity.ok(userService.changePassword(userPasswordDto));
    }

}
