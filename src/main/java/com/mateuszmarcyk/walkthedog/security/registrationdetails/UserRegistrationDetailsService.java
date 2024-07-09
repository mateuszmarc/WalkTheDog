package com.mateuszmarcyk.walkthedog.security.registrationdetails;

import com.mateuszmarcyk.walkthedog.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRegistrationDetailsService implements UserDetailsService {

   @Value("${userWithEmailNotFoundExceptionMessage}")
   private String userWithEmailNotFoundExceptionMessage;

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Loading user by email: {}", email);
        UserDetails userDetails = userRepository
                .findByEmail(email)
                .map(UserRegistrationDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(userWithEmailNotFoundExceptionMessage.formatted(email)));
        log.info("UserDetails loaded: {}", userDetails);
        return userDetails;
    }
}
