package com.mateuszmarcyk.walkthedog.dog;

import java.util.List;

public interface DogService {

    Dog save(Dog dog);

    Dog findById(Long id);

    List<Dog> getAll();

    Dog deleteById(Long id);

}
