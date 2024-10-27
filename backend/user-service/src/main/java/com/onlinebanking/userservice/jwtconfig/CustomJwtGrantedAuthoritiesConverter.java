package com.onlinebanking.userservice.jwtconfig;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        // Add roles from the 'roles' claim with ROLE_ prefix
        if (jwt.getClaims().containsKey("roles")) {
            List<String> roles = (List<String>) jwt.getClaims().get("roles");
            authorities.addAll(roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // Prefix with ROLE_
                    .toList());
        }

        // Add scopes from the 'scopes' claim
        if (jwt.getClaims().containsKey("scopes")) {
            List<String> scopes = (List<String>) jwt.getClaims().get("scopes");
            authorities.addAll(scopes.stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList());
        }
        return authorities;
    }
}
