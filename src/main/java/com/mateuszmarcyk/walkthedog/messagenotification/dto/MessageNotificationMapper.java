package com.mateuszmarcyk.walkthedog.messagenotification.dto;

import com.mateuszmarcyk.walkthedog.messagenotification.MessageNotification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageNotificationMapper {
    MessageNotificationMapper INSTANCE = Mappers.getMapper(MessageNotificationMapper.class);

    MessageNotification toEntity(MessageNotificationDTO messageNotificationDTO);

    MessageNotificationDTO toDTO(MessageNotification messageNotification);
}
