package com.mateuszmarcyk.walk_the_dog.registration;

import com.mateuszmarcyk.walk_the_dog.event.RegistrationCompleteEvent;
import com.mateuszmarcyk.walk_the_dog.model.User;
import com.mateuszmarcyk.walk_the_dog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/register")
public class RegistrationController {

    private static final String REGISTRATION_SUCCESS_MESSAGE =
            "Success! Please check your email to confirm your registration.";


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

}
