package com.mateuszmarcyk.walk_the_dog.repository;

import com.mateuszmarcyk.walk_the_dog.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);
}
