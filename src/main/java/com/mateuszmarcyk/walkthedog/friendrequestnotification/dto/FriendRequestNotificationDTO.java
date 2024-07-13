package com.mateuszmarcyk.walkthedog.friendrequestnotification.dto;

import com.mateuszmarcyk.walkthedog.friendrequest.dto.FriendRequestDTO;
import com.mateuszmarcyk.walkthedog.notification.NotificationStatus;
import com.mateuszmarcyk.walkthedog.user.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FriendRequestNotificationDTO {

    private Long id;

    private UserDTO receiver;

    private FriendRequestDTO friendRequest;

    private NotificationStatus status;

    public FriendRequestNotificationDTO(UserDTO receiver, FriendRequestDTO friendRequest, NotificationStatus status) {
        this.receiver = receiver;
        this.friendRequest = friendRequest;
        this.status = status;
    }
}

