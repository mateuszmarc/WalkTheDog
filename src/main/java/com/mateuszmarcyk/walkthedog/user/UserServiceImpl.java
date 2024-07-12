package com.mateuszmarcyk.walkthedog.user;

import com.mateuszmarcyk.walkthedog.exception.ResourceNotFoundException;
import com.mateuszmarcyk.walkthedog.exception.UserAlreadyExistsException;
import com.mateuszmarcyk.walkthedog.registration.RegistrationRequest;
import com.mateuszmarcyk.walkthedog.registration.token.VerificationToken;
import com.mateuszmarcyk.walkthedog.registration.token.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private static final String USER_ALREADY_EXISTS_MESSAGE = "User with email '%s' already exists.";

    @Value("${resourceNotFoundExceptionMessage}")
    private String resourceNotFoundExceptionMessage;

    @Value("${userWithEmailNotFoundExceptionMessage}")
    private String userWithEmailNotFoundExceptionMessage;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;


    @Override
    public User findById(Long id) {
        Optional<User> foundUser = userRepository.findById(id);

        return foundUser.orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("User", id)));
    }

    @Override
    public User findByIdJoinFetchDogs(Long id) {
        User user = userRepository.findUserJoinFetchDogs(id);

        if (user == null) {
            throw new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("User", id));
        }
        return user;
    }

    @Override
    public User findByEmailJoinFetchDogs(String email) {
        User user = userRepository.findUserJoinFetchDogs(email);
        if (user == null) {
            throw new UsernameNotFoundException(userWithEmailNotFoundExceptionMessage.formatted(email));
        }
        return user;
    }


    @Override
    public User findByEmailFetchFriends(String email) {

        Optional<User> foundUser = userRepository.findUserByEmailFetchFriends(email);
        return foundUser.orElseThrow(() -> new ResourceNotFoundException(userWithEmailNotFoundExceptionMessage.formatted("User", email)));

    }

    @Override
    public User findByEmailFetchConversations(String email) {

        Optional<User> foundUser = userRepository.findByEmailFetchConversations(email);
        return foundUser.orElseThrow(() -> new ResourceNotFoundException(userWithEmailNotFoundExceptionMessage.formatted("User", email)));

    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User register(RegistrationRequest request) {

        Optional<User> foundUser = userRepository.findByEmail(request.getEmail());

        if (foundUser.isPresent()) {
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS_MESSAGE.formatted(request.getEmail()));
        }

        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole()
        );

        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        return user.orElseThrow(() -> new UsernameNotFoundException(userWithEmailNotFoundExceptionMessage.formatted(email)));
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

    @Override
    public User save(User user) {

//        String plainPassword = user.getPassword();
//
//        user.setPassword(passwordEncoder.encode(plainPassword));

       return userRepository.save(user);

    }


}
