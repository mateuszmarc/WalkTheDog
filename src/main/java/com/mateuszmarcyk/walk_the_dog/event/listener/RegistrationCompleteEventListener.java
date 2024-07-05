package com.mateuszmarcyk.walk_the_dog.event.listener;

import com.mateuszmarcyk.walk_the_dog.event.RegistrationCompleteEvent;
import com.mateuszmarcyk.walk_the_dog.model.User;
import com.mateuszmarcyk.walk_the_dog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

        User user = event.getUser();

        String verificationToken = UUID.randomUUID().toString();

        userService.saveVerificationToken(user, verificationToken);

        String url = event.getApplicationUrl() + "/register/verifyEmail?token=" + verificationToken;
        log.info("Click the link to complete your registration: {}", url);
    }
}
