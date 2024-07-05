package com.mateuszmarcyk.walk_the_dog.service;

import com.mateuszmarcyk.walk_the_dog.model.User;
import com.mateuszmarcyk.walk_the_dog.registration.RegistrationRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();

    User register(RegistrationRequest request);

    Optional<User> findByEmail(String email);

    void saveVerificationToken(User user, String verificationToken);
}
