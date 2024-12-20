package com.onlinebanking.userservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/user")
public class TestController {

    @GetMapping("/hello1")
    @PreAuthorize("hasAuthority('CREATE_ACCOUNT')") // working with authority // working as expected
    public String hello1() {
        return "Hello1 from User Service!";
    }

    @GetMapping("/hello2")
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')") // working with role // not working as expected
    public String hello2() {
        return "Hello2 from User Service!";
    }

    @GetMapping("/hello3")
    @PreAuthorize("hasRole('CUSTOMER')") // working with Role // working as expected
    public String hello3() {
        return "Hello3 from User Service!";
    }

    @GetMapping("/hello4")
    @PreAuthorize("hasAuthority('VIEW_ACCOUNT')") // working with authority // working as expected
    public String hello4() {
        return "Hello4 from User Service!";
    }

    @GetMapping("/hello5")
    @PreAuthorize("hasRole('SCOPE_PAY_BILLS')") // forbidden // working as expected
    public String hello5() {
        return "Hello5 from User Service!";
    }

    @GetMapping("/hello6")
    @PreAuthorize("hasRole('VIEW_ACCOUNT')") // forbidden // working as expected
    public String hello6() {
        return "Hello6 from User Service!";
    }

    @GetMapping("/hello7")
    @PreAuthorize("hasAuthority('SCOPE_VIEW_ACCOUNT')") // forbidden // working as expected
    public String hello7() {
        return "Hello7 from User Service!";
    }

    @GetMapping("/hello8")
    @PreAuthorize("hasAuthority('CUSTOMER')") // forbidden // working as expected
    public String hello8() {
        return "Hello8 from User Service!";
    }
}
