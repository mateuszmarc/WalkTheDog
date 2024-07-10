package com.mateuszmarcyk.walkthedog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
public class UserController {

private final UserService userService;

    @GetMapping
    @ResponseBody
    public List<User> findAll() {
        return userService.getAll();
    }

    @GetMapping("/dashboard")
    public String displayDashboard(@AuthenticationPrincipal UserDetails userDetails) {

        return "dashboard";
    }

    @GetMapping("/profile")
    public String displayProfileDetails(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        String email = userDetails.getUsername();

        User user = userService.findUserByEmailJoinFetchDogs(email);

        model.addAttribute("user", user);

        return "user-profile";

    }

    @GetMapping("/proflie/edit")
    public String showUserEditForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);

        model.addAttribute("user", user);

        return "user-edit-form";
    }
}
