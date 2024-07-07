package com.mateuszmarcyk.walkthedog.login;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class LoginController {

    @GetMapping("/login")
    public String displayLoginForm() {
        return "login";
    }


    @PostMapping("/login")
    @ResponseBody
    public String processForm(HttpServletRequest request) {
        log.info("{}", request.getParameter("email"));
        log.info("{}", request.getParameter("password"));
        return "redirect:/users";
    }

}
