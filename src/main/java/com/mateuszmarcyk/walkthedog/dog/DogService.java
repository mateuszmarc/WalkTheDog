package com.mateuszmarcyk.walkthedog.dog;

import java.util.List;

public interface DogService {

    Dog add(Dog dog);

    Dog findById(Long id);

    List<Dog> getAll();

    Dog delete(Long id);

}
