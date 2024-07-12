package com.mateuszmarcyk.walkthedog.dog.dto;

import com.mateuszmarcyk.walkthedog.dog.Dog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DogMapper {
    DogMapper INSTANCE = Mappers.getMapper(DogMapper.class);

    Dog toEntity(DogDTO dogDTO);

    DogDTO toDTO(Dog dog);
}
