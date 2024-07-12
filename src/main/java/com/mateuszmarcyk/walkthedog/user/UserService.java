package com.mateuszmarcyk.walkthedog.user;

import com.mateuszmarcyk.walkthedog.registration.RegistrationRequest;

import java.util.List;


public interface UserService {

    User findById(Long id);

    User findByEmail(String email);

    User findByIdJoinFetchDogs(Long id);

    User findByEmailJoinFetchDogs(String email);

    User findByEmailFetchFriends(String email);

    User findByEmailFetchConversations(String email);


    List<User> getAll();

    User register(RegistrationRequest request);

    void saveVerificationToken(User user, String verificationToken);

    String validateToken(String token);

    User save(User user);
}
