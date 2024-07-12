package com.mateuszmarcyk.walkthedog.friendrequestnotification.dto;

import com.mateuszmarcyk.walkthedog.friendrequestnotification.FriendRequestNotification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FriendRequestNotificationMapper {
    FriendRequestNotificationMapper INSTANCE = Mappers.getMapper(FriendRequestNotificationMapper.class);

    FriendRequestNotification toEntity(FriendRequestNotificationDTO friendRequestNotificationDTO);

    FriendRequestNotificationDTO toDTO(FriendRequestNotification friendRequestNotification);
}
