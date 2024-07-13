package com.mateuszmarcyk.walkthedog.conversation.dto;

import com.mateuszmarcyk.walkthedog.conversation.Conversation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ConversationMapper {
    ConversationMapper INSTANCE = Mappers.getMapper(ConversationMapper.class);

    Conversation toEntity(ConversationDTO conversationDTO);

    ConversationDTO toDTO(Conversation conversation);
}

