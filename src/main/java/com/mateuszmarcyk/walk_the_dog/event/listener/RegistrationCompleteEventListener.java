package com.mateuszmarcyk.walk_the_dog.event.listener;

import com.mateuszmarcyk.walk_the_dog.event.RegistrationCompleteEvent;
import com.mateuszmarcyk.walk_the_dog.model.AppUser;
import com.mateuszmarcyk.walk_the_dog.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final AppUserService appUserService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

        AppUser appUser = event.getAppUser();

        String verificationToken = UUID.randomUUID().toString();

        appUserService.saveVerificationToken(appUser, verificationToken);

        String url = event.getApplicationUrl() + "/register/verifyEmail?token=" + verificationToken;
        log.info("Click the link to complete your registration: {}", url);
    }
}
