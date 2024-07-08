package com.mateuszmarcyk.walkthedog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

private final UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.getAll();
    }

    @GetMapping("/dashboard")
    public String displayDashboard(@AuthenticationPrincipal UserDetails userDetails) {

        return userDetails.toString();
    }


}
