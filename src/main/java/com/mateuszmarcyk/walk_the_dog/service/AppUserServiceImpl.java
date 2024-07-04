package com.mateuszmarcyk.walk_the_dog.service;

import com.mateuszmarcyk.walk_the_dog.exception.UserAlreadyExistsException;
import com.mateuszmarcyk.walk_the_dog.model.AppUser;
import com.mateuszmarcyk.walk_the_dog.registration.RegistrationRequest;
import com.mateuszmarcyk.walk_the_dog.registration.token.VerificationToken;
import com.mateuszmarcyk.walk_the_dog.registration.token.VerificationTokenRepository;
import com.mateuszmarcyk.walk_the_dog.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private static final String USER_ALREADY_EXISTS_MESSAGE = "User with email '%s' already exists.";

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public List<AppUser> getAll() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser register(RegistrationRequest request) {

        Optional<AppUser> foundUser = appUserRepository.findByEmail(request.email());

        if (foundUser.isPresent()) {
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS_MESSAGE.formatted(request.email()));
        }

        AppUser appUser = new AppUser();

        appUser.setFirstName(request.firstName());
        appUser.setLastName(request.lastName());
        appUser.setUsername(request.username());
        appUser.setEmail(request.email());
        appUser.setPassword(passwordEncoder.encode(request.password()));
        appUser.setAppUserRole(request.role());


        return appUserRepository.save(appUser);
    }

    @Override
    public Optional<AppUser> findByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    @Override
    public void saveVerificationToken(AppUser appUser, String verificationToken) {

        VerificationToken token = new VerificationToken(verificationToken, appUser);
        verificationTokenRepository.save(token);
    }


}
