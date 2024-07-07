package com.mateuszmarcyk.walkthedog.registration;

import com.mateuszmarcyk.walkthedog.registration.token.VerificationToken;
import com.mateuszmarcyk.walkthedog.registration.token.VerificationTokenRepository;
import com.mateuszmarcyk.walkthedog.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/register")
@Controller
public class RegistrationController {

    private static final String REGISTRATION_SUCCESS_MESSAGE =
            "Success! Please check your email to confirm your registration.";


    private final VerificationTokenRepository tokenRepository;
    private final UserService userService;
    private final  ApplicationEventPublisher publisher;

    @GetMapping
    public String displayRegistrationForm(Model model) {
        model.addAttribute("request", new RegistrationRequest());

        return "register";
    }

    @PostMapping
    @ResponseBody
    public String registerUser(RegistrationRequest request, HttpServletRequest httpServletRequest) {

        log.info("{}", request);
//        User user = userService.register(request);

//        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(httpServletRequest)));
        return REGISTRATION_SUCCESS_MESSAGE;
    }

    public String applicationUrl(HttpServletRequest httpServletRequest) {
        return "http://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath();
    }

    @GetMapping("/verifyEmail")
    @ResponseBody
    public String verifyUser(@RequestParam String token) {

        VerificationToken verificationToken = tokenRepository.findByToken(token);

        if (verificationToken == null) {
            return "Invalid verification token";
        }

        if (verificationToken.getUser().getEnabled()) {
            return "Your account has already been verified. Please login";
        }

        String verificationResult = userService.validateToken(verificationToken.getToken());

        if (verificationResult.equals("valid")) {
            return "You email has been verified successfully";
        } else {
            return "Invalid verification token";
        }

    }
}
