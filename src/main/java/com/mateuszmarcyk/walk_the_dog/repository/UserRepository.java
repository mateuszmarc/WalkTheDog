package com.mateuszmarcyk.walk_the_dog.repository;

import com.mateuszmarcyk.walk_the_dog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
