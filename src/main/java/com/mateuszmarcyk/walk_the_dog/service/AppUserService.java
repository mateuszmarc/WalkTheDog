package com.mateuszmarcyk.walk_the_dog.service;

import com.mateuszmarcyk.walk_the_dog.model.AppUser;
import com.mateuszmarcyk.walk_the_dog.registration.RegistrationRequest;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

    List<AppUser> getAll();

    AppUser register(RegistrationRequest request);

    Optional<AppUser> findByEmail(String email);

    void saveVerificationToken(AppUser appUser, String verificationToken);
}
