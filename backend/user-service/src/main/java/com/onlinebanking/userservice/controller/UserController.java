package com.onlinebanking.userservice.controller;

import com.onlinebanking.userservice.dto.UserDto;
import com.onlinebanking.userservice.entity.User;
import com.onlinebanking.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public String hello() {
        return "Hello from User Service!";
    }

    @PostMapping ResponseEntity<Object> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Object> getUserDetails(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserDetails(username));
    }

    @PutMapping("/username/{username}")
    public ResponseEntity<Object> updateUserDetails(@PathVariable String username, @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.updateUserDetails(username, userDto));
    }
}
