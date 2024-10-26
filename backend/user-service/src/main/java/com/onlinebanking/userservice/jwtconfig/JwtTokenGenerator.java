package com.onlinebanking.userservice.jwtconfig;

import com.onlinebanking.userservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtTokenGenerator {

    private final JwtEncoder jwtEncoder;
    private final RoleRepository roleRepository;

    public String generateAccessToken(Authentication authentication) {
        log.info("[JwtTokenGenerator:generateAccessToken] Token Creation Started for: {}", authentication.getName());

        // Get user roles from authentication
        Set<String> roles = getRolesOfUser(authentication);

        // Get permissions based on user roles
        Set<String> permissions = getPermissionsFromRoles(roles);

        // Create JWT claims
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("tejajagdeep")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(15, ChronoUnit.MINUTES))
                .subject(authentication.getName())
                .claim("role", roles)
                .claim("scope", permissions)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private Set<String> getRolesOfUser(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }

    private Set<String> getPermissionsFromRoles(Set<String> roles) {
        // Fetch roles and their permissions from the database, eagerly loading permissions
        return roleRepository.findAllByRoleNameIn(roles).stream()
                .flatMap(role -> role.getRolePermissions().stream()
                        .map(rolePermission -> rolePermission.getPermission().getPermissionName()))
                .collect(Collectors.toSet());
    }

}
