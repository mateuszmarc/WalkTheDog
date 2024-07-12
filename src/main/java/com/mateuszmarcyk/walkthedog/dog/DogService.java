package com.mateuszmarcyk.walkthedog.dog;

import com.mateuszmarcyk.walkthedog.user.User;

import java.util.List;

public interface DogService {

    Dog save(Dog dog);

    Dog save(Dog dog, User user);

    Dog findById(Long id);

    List<Dog> getAll();

    Dog deleteById(Long id, User user);

}
