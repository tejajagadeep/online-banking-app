package com.onlinebanking.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleAssignmentResponse {
    private Long userId;
    private Long roleId;
    private String username;
    private String roleName;
}

