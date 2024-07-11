package com.mateuszmarcyk.walkthedog.user;

import com.mateuszmarcyk.walkthedog.registration.RegistrationRequest;

import java.util.List;


public interface UserService {

    User findById(Long id);

    User findUserByIdJoinFetchDogs(Long id);

    User findUserByEmailJoinFetchDogs(String email);

    User findUserByEmailFetchFriends(String email);

    User findByEmailFetchConversations(String email);


    List<User> getAll();

    User register(RegistrationRequest request);

    User findByEmail(String email);

    void saveVerificationToken(User user, String verificationToken);

    String validateToken(String token);

    User save(User user);
}
