package com.onlinebanking.userservice.controller;

import com.onlinebanking.userservice.dto.UserPasswordDto;
import com.onlinebanking.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/change-password")
    @PreAuthorize("hasAnyAuthority('CREATE_ACCOUNT')")
    public ResponseEntity<Object> changePassword(@Valid @RequestBody UserPasswordDto userPasswordDto){
        return ResponseEntity.ok(userService.changePassword(userPasswordDto));
    }

}
