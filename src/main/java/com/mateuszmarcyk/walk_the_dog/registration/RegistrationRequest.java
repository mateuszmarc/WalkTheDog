package com.mateuszmarcyk.walk_the_dog.registration;

public record RegistrationRequest(
        String firstName,
        String lastName,
        String username,
        String email,
        String password,
        String role
) {
}
