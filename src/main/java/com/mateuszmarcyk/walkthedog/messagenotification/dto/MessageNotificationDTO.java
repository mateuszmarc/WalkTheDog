package com.mateuszmarcyk.walkthedog.messagenotification.dto;

import com.mateuszmarcyk.walkthedog.message.dto.MessageDTO;
import com.mateuszmarcyk.walkthedog.notification.NotificationStatus;
import com.mateuszmarcyk.walkthedog.user.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageNotificationDTO {

    private Long id;
    private UserDTO receiver;
    private NotificationStatus notificationStatus;
    private MessageDTO message;

    public MessageNotificationDTO(UserDTO receiver, NotificationStatus notificationStatus, MessageDTO message) {
        this.receiver = receiver;
        this.notificationStatus = notificationStatus;
        this.message = message;
    }
}

