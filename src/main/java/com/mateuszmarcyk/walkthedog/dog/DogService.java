package com.mateuszmarcyk.walkthedog.dog;

import com.mateuszmarcyk.walkthedog.dog.dto.DogDTO;
import com.mateuszmarcyk.walkthedog.user.User;

import java.util.List;

public interface DogService {

    DogDTO save(DogDTO dogDTO);

    DogDTO save(DogDTO dogDTO, User user);

    DogDTO findById(Long id);

    List<DogDTO> getAll();

    DogDTO deleteById(Long id, User user);

}
