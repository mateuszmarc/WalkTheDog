package com.mateuszmarcyk.walkthedog.message.dto;

import com.mateuszmarcyk.walkthedog.message.Message;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    Message toEntity(MessageDTO messageDTO);

    MessageDTO toDTO(Message message);
}
