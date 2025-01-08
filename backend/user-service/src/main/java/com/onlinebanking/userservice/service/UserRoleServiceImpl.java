package com.onlinebanking.userservice.service;

import com.onlinebanking.userservice.model.Role;
import com.onlinebanking.userservice.model.User;
import com.onlinebanking.userservice.model.UserRole;
import com.onlinebanking.userservice.repository.RoleRepository;
import com.onlinebanking.userservice.repository.UserRepository;
import com.onlinebanking.userservice.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserRole assignRole(String username, String roleName) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Role role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new UsernameNotFoundException("Role not found with roleName: " + roleName));

        return userRoleRepository.save(new UserRole(user, role));
    }
}
