package com.mateuszmarcyk.walk_the_dog.security;

import com.mateuszmarcyk.walk_the_dog.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserRegistrationDetailsService implements UserDetailsService {

    private static String USER_NOT_FOUND_MESSAGE = "User with email: '%s' not found";

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository
                .findByEmail(email)
                .map(UserRegistrationDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE.formatted(email)));
    }
}
