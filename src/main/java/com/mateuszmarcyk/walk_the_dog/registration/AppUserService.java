package com.mateuszmarcyk.walk_the_dog.registration;

import com.mateuszmarcyk.walk_the_dog.model.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

    List<AppUser> getAll();

    AppUser save(RegistrationRequest request);

    Optional<AppUser> findByEmail(String email);
}
