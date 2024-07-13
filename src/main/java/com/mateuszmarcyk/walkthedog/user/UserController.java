package com.mateuszmarcyk.walkthedog.user;

import com.mateuszmarcyk.walkthedog.user.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/dashboard")
    public String displayDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        String email = userDetails.getUsername();

        UserDTO userDTO = userService.findByEmail(email);

        model.addAttribute("userDTO", userDTO);

        return "dashboard";
    }

    @GetMapping("/profile")
    public String displayProfileDetails(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        String email = userDetails.getUsername();

        UserDTO userDTO = userService.findByEmailJoinFetchDogs(email);

        model.addAttribute("user", userDTO);

        return "user-profile";

    }

    @GetMapping("/profile/edit")
    public String showUserEditForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        String email = userDetails.getUsername();
        UserDTO userDTO = userService.findByEmail(email);

        model.addAttribute("userDTO", userDTO);

        return "user-edit-form";
    }

    @PostMapping("/profile/edit")
    public String processForm(@Valid @ModelAttribute UserDTO userDTO,
                              BindingResult bindingResult,
                              @AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername();

        User loggedInUser = userService.findUserByEmail(email);

        if (bindingResult.hasErrors()) {

            bindingResult.getAllErrors().forEach(error -> log.info("{}", error.toString()));

            return "user-edit-form";
        }

        log.info("Updated user form: {}", userDTO);

        userService.save(loggedInUser, userDTO);

        return "redirect:/users/profile/edit";
    }

    @GetMapping("/friends")
    public String showAllFriends(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        String email = userDetails.getUsername();

        UserDTO userDTO = userService.findByEmailFetchFriends(email);

        log.info(userDTO.toString());
        model.addAttribute("user", userDTO);

        return "user-friends";
    }


    @PostMapping("/profile/editPassword")
    private String changePassword(@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult, @AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request, Model model) {

        User user = userService.findUserByEmail(userDetails.getUsername());

        String plainPassword = userDTO.getPassword();
        String plainPasswordRepeat = request.getParameter("passwordRepeat");

        String info = "Niepowodzenie. Hasła muszą być identyczne";


        if (plainPassword.equals(plainPasswordRepeat)) {

            if (!bindingResult.hasErrors()) {

                userService.changePassword(user, plainPassword);

                info = "Sukces. Hasło zostało zmienione";
            }
        }


        model.addAttribute("info", info);
        return "user-edit-form";
    }
}
