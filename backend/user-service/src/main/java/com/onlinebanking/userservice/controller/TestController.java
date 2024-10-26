package com.onlinebanking.userservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/user")
public class TestController {


    @GetMapping("/hello1")
    @PreAuthorize("hasAuthority('SCOPE_CREATE_ACCOUNT')") // Check for specific scope
    public String hello1() {
        return "Hello1 from User Service!";
    }

    @GetMapping("/hello2")
    @PreAuthorize("hasRole('AUDITOR')") // Check for AUDITOR role
    public String hello2() {
        return "Hello2 from User Service!";
    }

    @GetMapping("/hello3")
    @PreAuthorize("hasRole('ANALYZE_FINANCIAL_DATA')") // Check for ANALYZE_FINANCIAL_DATA role
    public String hello3() {
        return "Hello3 from User Service!";
    }

    @GetMapping("/hello4")
    @PreAuthorize("hasAuthority('SCOPE_CREATE_ACCOUNT') and hasRole('ANALYZE_FINANCIAL_DATA')") // Check for both
    public String hello4() {
        return "Hello4 from User Service!";
    }

    @GetMapping("/hello5")
    @PreAuthorize("hasAuthority('SCOPE_CREATE_ACCOUNT') or hasRole('AUDITOR')") // Check for either
    public String hello5() {
        return "Hello5 from User Service!";
    }

    @GetMapping("/hello6")
    @PreAuthorize("hasAuthority('SCOPE_CREATE_ACCOUNT') and hasRole('AUDITOR')") // Check for both
    public String hello6() {
        return "Hello6 from User Service!";
    }
}
