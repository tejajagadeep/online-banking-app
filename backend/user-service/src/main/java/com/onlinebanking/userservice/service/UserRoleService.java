package com.onlinebanking.userservice.service;

import com.onlinebanking.userservice.model.UserRole;

public interface UserRoleService {
    UserRole assignRole(String username, String roleName);
}
