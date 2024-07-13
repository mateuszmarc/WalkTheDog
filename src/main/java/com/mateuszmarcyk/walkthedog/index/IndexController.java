package com.mateuszmarcyk.walkthedog.index;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {



    @GetMapping("/main")
    public String displayIndexPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        if (userDetails != null) {
            model.addAttribute("authenticated", true);
        }

        return "app-index";
    }

    @GetMapping("/about")
    public String displayAboutPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        if (userDetails != null) {
            model.addAttribute("authenticated", true);
        }
        return "app-about";
    }

    @GetMapping("/contact")
    public String displayContactPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        if (userDetails != null) {
            model.addAttribute("authenticated", true);
        }

        return "app-contact";
    }

}
