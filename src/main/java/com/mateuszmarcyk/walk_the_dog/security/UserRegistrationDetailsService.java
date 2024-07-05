package com.mateuszmarcyk.walk_the_dog.security;

import com.mateuszmarcyk.walk_the_dog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationDetailsService implements UserDetailsService {

    private static String USER_NOT_FOUND_MESSAGE = "User with email: '%s' not found";

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(UserRegistrationDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE.formatted(email)));
    }
}
