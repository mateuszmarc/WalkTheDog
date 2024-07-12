package com.mateuszmarcyk.walkthedog.dog.dto;

import com.mateuszmarcyk.walkthedog.dog.Dog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface DogMapper {
    DogMapper INSTANCE = Mappers.getMapper(DogMapper.class);

    @Mapping(source="owner", target="owner")
    Dog toEntity(DogDTO dogDTO);

    @Mapping(source = "owner", target = "owner")
    DogDTO toDTO(Dog dog);
}
