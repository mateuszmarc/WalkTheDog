package com.mateuszmarcyk.walkthedog.user;

import com.mateuszmarcyk.walkthedog.registration.RegistrationRequest;
import com.mateuszmarcyk.walkthedog.user.dto.UserDTO;

import java.util.List;


public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    User findUserById(Long id);

    UserDTO findByEmail(String email);

    User findUserByEmail(String email);

    UserDTO findByIdJoinFetchDogs(Long id);

    UserDTO findByEmailJoinFetchDogs(String email);

    UserDTO findByEmailFetchFriends(String email);

    UserDTO findByEmailFetchConversations(String email);


    User register(RegistrationRequest request);

    void saveVerificationToken(User user, String verificationToken);

    String validateToken(String token);

    UserDTO save(User user);

    UserDTO updatePassword(User user, String password);
}
