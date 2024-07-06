package com.mateuszmarcyk.walkthedog.security.registrationdetails;

import com.mateuszmarcyk.walkthedog.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRegistrationDetailsService implements UserDetailsService {

    private static String USER_NOT_FOUND_MESSAGE = "User with email: '%s' not found";

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Loading user by email: {}", email);
        UserDetails userDetails = userRepository
                .findByEmail(email)
                .map(UserRegistrationDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE.formatted(email)));
        log.info("UserDetails loaded: {}", userDetails);
        return userDetails;
    }
}
