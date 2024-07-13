package com.mateuszmarcyk.walkthedog.walkinvitationnotification.dto;

import com.mateuszmarcyk.walkthedog.walkinvitationnotification.WalkEventInvitationNotification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalkEventInvitationNotificationMapper {
    WalkEventInvitationNotificationMapper INSTANCE = Mappers.getMapper(WalkEventInvitationNotificationMapper.class);

    WalkEventInvitationNotification toEntity(WalkEventInvitationNotificationDTO walkEventInvitationNotificationDTO);

    WalkEventInvitationNotificationDTO toDTO(WalkEventInvitationNotification walkEventInvitationNotification);
}
