package com.mateuszmarcyk.walkthedog.friendrequest.dto;

import com.mateuszmarcyk.walkthedog.friendrequest.enums.RequestStatus;
import com.mateuszmarcyk.walkthedog.friendrequestnotification.dto.FriendRequestNotificationDTO;
import com.mateuszmarcyk.walkthedog.user.dto.UserDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FriendRequestDTO {

    private Long id;

    @NotNull(message = "Request status is required")
    private RequestStatus requestStatus;

    @NotNull(message = "Sent date is required")
    private LocalDateTime sentAt;

    private LocalDateTime respondedAt;

    private String message;

    @NotNull(message = "Sender is required")
    private UserDTO sender;

    @NotNull(message = "Receiver is required")
    private UserDTO receiver;

    private FriendRequestNotificationDTO friendRequestNotification;
}