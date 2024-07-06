package com.mateuszmarcyk.walk_the_dog.registration;

import com.mateuszmarcyk.walk_the_dog.event.RegistrationCompleteEvent;
import com.mateuszmarcyk.walk_the_dog.model.User;
import com.mateuszmarcyk.walk_the_dog.registration.token.VerificationToken;
import com.mateuszmarcyk.walk_the_dog.registration.token.VerificationTokenRepository;
import com.mateuszmarcyk.walk_the_dog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/register")
public class RegistrationController {

    private static final String REGISTRATION_SUCCESS_MESSAGE =
            "Success! Please check your email to confirm your registration.";


    private final VerificationTokenRepository tokenRepository;
    private final UserService userService;
    private final  ApplicationEventPublisher publisher;

    @PostMapping
    public String registerUser(@RequestBody RegistrationRequest request, HttpServletRequest httpServletRequest) {

        User user = userService.register(request);

        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(httpServletRequest)));
        return REGISTRATION_SUCCESS_MESSAGE;
    }

    public String applicationUrl(HttpServletRequest httpServletRequest) {
        return "http://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath();
    }

    @GetMapping("/verifyEmail")
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
