package com.mateuszmarcyk.walkthedog.walkeventinvitation.dto;

import com.mateuszmarcyk.walkthedog.walkeventinvitation.WalkEventInvitation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalkEventInvitationMapper {
    WalkEventInvitationMapper INSTANCE = Mappers.getMapper(WalkEventInvitationMapper.class);

    WalkEventInvitation toEntity(WalkEventInvitationDTO walkEventInvitationDTO);

    WalkEventInvitationDTO toDTO(WalkEventInvitation walkEventInvitation);
}
