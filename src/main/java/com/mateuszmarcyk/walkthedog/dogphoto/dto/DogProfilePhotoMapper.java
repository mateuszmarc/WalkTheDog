package com.mateuszmarcyk.walkthedog.dogphoto.dto;

import com.mateuszmarcyk.walkthedog.dogphoto.DogProfilePhoto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DogProfilePhotoMapper {
    DogProfilePhotoMapper INSTANCE = Mappers.getMapper(DogProfilePhotoMapper.class);

    DogProfilePhoto toEntity(DogProfilePhotoDTO dogProfilePhotoDTO);

    DogProfilePhotoDTO toDTO(DogProfilePhoto dogProfilePhoto);
}
