package com.mateuszmarcyk.walkthedog.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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

        User user = userService.findByEmailJoinFetchDogs(email);

        model.addAttribute("user", user);

        return "user-profile";

    }

    @GetMapping("/profile/edit")
    public String showUserEditForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);

        model.addAttribute("user", user);

        return "user-edit-form";
    }

    @PostMapping("/profile/edit")
    public String processForm(@ModelAttribute User user) {

        log.info("Updated user form: {}", user);

        userService.save(user);

        return "redirect:/users/dashboard";
    }

    @GetMapping("/friends")
    public String showAllFriends(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        String email = userDetails.getUsername();

        User user = userService.findByEmailFetchFriends(email);

        log.info(user.toString());
        model.addAttribute("user", user);

        return "user-friends";
    }
}
