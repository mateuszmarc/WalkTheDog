package com.mateuszmarcyk.walkthedog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM User u JOIN FETCH u.dogs WHERE u.id=:userId")
    User findUserJoinFetchDogs(Long userId);

    @Query(value = "SELECT u from User u JOIN fetch u.dogs WHERE u.email=:userEmail")
    User findUserJoinFetchDogs(String userEmail);

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT u FROM User u JOIN FETCH u.sentFriendRequests " +
            "JOIN FETCH u.dogs " +
            "JOIN FETCH u.sentFriendRequests " +
            "JOIN FETCH u.receivedFriendRequests " +
            "JOIN FETCH u.friends " +
            "JOIN FETCH u.conversations " +
            "JOIN FETCH u.walkEvents " +
            "JOIN FETCH u.friendRequestNotifications " +
            "JOIN FETCH u.walkEventInvitationNotifications " +
            "JOIN FETCH u.messageNotifications " +
            "JOIN FETCH u.walkInvitations " +
            "JOIN FETCH u.sentWalkInvitations " +
            "JOIN FETCH u.receivedWalkInvitations " +
            "JOIN FETCH u.createdWalkEvents"
            )
    Optional<User> findUserByEmailFetchAll(String email);
}
