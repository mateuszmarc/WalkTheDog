package com.mateuszmarcyk.walkthedog.dogfunfact.dto;

import com.mateuszmarcyk.walkthedog.dog.Dog;
import com.mateuszmarcyk.walkthedog.dog.dto.DogDTO;
import com.mateuszmarcyk.walkthedog.dog.dto.DogMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DogFunFactMapper {
    DogMapper INSTANCE = Mappers.getMapper(DogMapper.class);

    Dog toEntity(DogDTO dogDTO);

    DogDTO toDTO(Dog dog);
}

