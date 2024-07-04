package com.mateuszmarcyk.walk_the_dog.registration;

import com.mateuszmarcyk.walk_the_dog.event.RegistrationCompleteEvent;
import com.mateuszmarcyk.walk_the_dog.model.AppUser;
import com.mateuszmarcyk.walk_the_dog.service.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/register")
public class RegistrationController {

    private static final String REGISTRATION_SUCCESS_MESSAGE =
            "Success! Please check your email to confirm your registration.";


    private final AppUserService appUserService;
    private final  ApplicationEventPublisher publisher;

    @PostMapping
    public String registerUser(RegistrationRequest request, HttpServletRequest httpServletRequest) {

        AppUser appUser = appUserService.register(request);

        publisher.publishEvent(new RegistrationCompleteEvent(appUser, applicationUrl(httpServletRequest)));
        return REGISTRATION_SUCCESS_MESSAGE;
    }

    public String applicationUrl(HttpServletRequest httpServletRequest) {
        return "http://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath();
    }
}
