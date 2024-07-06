package com.mateuszmarcyk.walkthedog.user;

import com.mateuszmarcyk.walkthedog.exception.ResourceNotFoundException;
import com.mateuszmarcyk.walkthedog.exception.UserAlreadyExistsException;
import com.mateuszmarcyk.walkthedog.registration.RegistrationRequest;
import com.mateuszmarcyk.walkthedog.registration.token.VerificationToken;
import com.mateuszmarcyk.walkthedog.registration.token.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER_ALREADY_EXISTS_MESSAGE = "User with email '%s' already exists.";

    @Value("${resourceNotFoundExceptionMessage}")
    private String getResourceNotFoundExceptionMessage;

    @Value("${resourceNotFoundExceptionMessage}")
    private String resourceNotFoundExceptionMessage;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;


    @Override
    public User findById(Long id) {
        Optional<User> foundUser = userRepository.findById(id);

        return foundUser.orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("User", id)));
    }

    @Override
    public User findUserByIdJoinFetchDogs(Long id) {
        User user = userRepository.findUserJoinFetchDogs(id);

        if (user == null) {
            throw new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("User", id));
        }
        return user;
    }


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
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            log.info("{}", user.get());
        } else {
            log.info("User is null");
        }
        return user;
    }

    @Override
    public void saveVerificationToken(User user, String verificationToken) {

        VerificationToken token = new VerificationToken(verificationToken, user);
        verificationTokenRepository.save(token);
    }

    @Override
    public String validateToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if (verificationToken == null) {
            return "Invalid verification token";
        }
        User user = verificationToken.getUser();

        Calendar calendar = Calendar.getInstance();

        if (verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            verificationTokenRepository.delete(verificationToken);
            return "Token already expired";
        }

        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

}
