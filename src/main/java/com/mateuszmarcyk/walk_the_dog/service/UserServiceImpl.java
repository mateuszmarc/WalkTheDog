package com.mateuszmarcyk.walk_the_dog.service;

import com.mateuszmarcyk.walk_the_dog.exception.UserAlreadyExistsException;
import com.mateuszmarcyk.walk_the_dog.model.User;
import com.mateuszmarcyk.walk_the_dog.registration.RegistrationRequest;
import com.mateuszmarcyk.walk_the_dog.registration.token.VerificationToken;
import com.mateuszmarcyk.walk_the_dog.registration.token.VerificationTokenRepository;
import com.mateuszmarcyk.walk_the_dog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER_ALREADY_EXISTS_MESSAGE = "User with email '%s' already exists.";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User register(RegistrationRequest request) {

        Optional<User> foundUser = userRepository.findByEmail(request.email());

        if (foundUser.isPresent()) {
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS_MESSAGE.formatted(request.email()));
        }

        User user = new User();

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setAppUserRole(request.role());


        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveVerificationToken(User user, String verificationToken) {

        VerificationToken token = new VerificationToken(verificationToken, user);
        verificationTokenRepository.save(token);
    }


}
