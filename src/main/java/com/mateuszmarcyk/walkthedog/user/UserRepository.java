package com.mateuszmarcyk.walkthedog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.dogs WHERE u.id=:userId")
    User findUserJoinFetchDogs(Long userId);

    @Query(value = "SELECT u from User u LEFT JOIN FETCH u.dogs WHERE u.email=:userEmail")
    User findUserJoinFetchDogs(String userEmail);

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT u FROM User u  LEFT JOIN FETCH u.friends WHERE u.email=:userEmail")
    Optional<User> findUserByEmailFetchFriends(String userEmail);


    @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.conversations WHERE u.email=:userEmail")
    Optional<User> findByEmailFetchConversations(String userEmail);

}
