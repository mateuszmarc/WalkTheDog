package com.mateuszmarcyk.walkthedog.user;

import com.mateuszmarcyk.walkthedog.registration.RegistrationRequest;

import java.util.List;
import java.util.Optional;


public interface UserService {

    User findById(Long id);

    List<User> getAll();

    User register(RegistrationRequest request);

    Optional<User> findByEmail(String email);

    void saveVerificationToken(User user, String verificationToken);

    String validateToken(String token);
}
