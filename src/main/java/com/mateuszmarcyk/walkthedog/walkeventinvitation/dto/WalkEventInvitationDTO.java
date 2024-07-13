package com.mateuszmarcyk.walkthedog.walkeventinvitation.dto;

import com.mateuszmarcyk.walkthedog.friendrequest.enums.RequestStatus;
import com.mateuszmarcyk.walkthedog.user.dto.UserDTO;
import com.mateuszmarcyk.walkthedog.walkevent.dto.WalkEventDTO;
import com.mateuszmarcyk.walkthedog.walkinvitationnotification.dto.WalkEventInvitationNotificationDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class WalkEventInvitationDTO {

    private Long id;
    private RequestStatus status;
    private LocalDateTime sentAt;
    private LocalDateTime respondedAt;
    private WalkEventDTO walkEvent;
    private UserDTO sender;
    private UserDTO receiver;
    private WalkEventInvitationNotificationDTO walkEventInvitationNotification;
}
