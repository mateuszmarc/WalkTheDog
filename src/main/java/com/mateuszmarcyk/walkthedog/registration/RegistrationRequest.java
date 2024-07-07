package com.mateuszmarcyk.walkthedog.registration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String passwordConfirm;
    private String role;
}
