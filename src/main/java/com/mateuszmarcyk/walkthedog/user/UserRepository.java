package com.mateuszmarcyk.walkthedog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM User u JOIN FETCH u.dogs WHERE u.id=:userId")
    User findUserJoinFetchDogs(Long userId);

    Optional<User> findByEmail(String email);
}
