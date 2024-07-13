package com.mateuszmarcyk.walkthedog.walkevent.dto;

import com.mateuszmarcyk.walkthedog.walkevent.WalkEvent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalkEventMapper {
    WalkEventMapper INSTANCE = Mappers.getMapper(WalkEventMapper.class);

    WalkEvent toEntity(WalkEventDTO walkEventDTO);

    WalkEventDTO toDTO(WalkEvent walkEvent);
}

