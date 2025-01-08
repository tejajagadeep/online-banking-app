package com.onlinebanking.userservice.controller;

import com.onlinebanking.userservice.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/api/v1.0/administrator")
public class AdministratorController {

    private final UserRoleService userRoleService;

    @PostMapping("/assign-role/{username}/{roleName}")
    public ResponseEntity<Object> assignRole(@PathVariable String username, @PathVariable String roleName){
        return new ResponseEntity<>(userRoleService.assignRole(username, roleName), HttpStatus.CREATED);
    }


}
