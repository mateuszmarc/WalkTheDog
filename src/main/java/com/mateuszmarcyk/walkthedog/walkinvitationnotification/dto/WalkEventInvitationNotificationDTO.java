package com.mateuszmarcyk.walkthedog.walkinvitationnotification.dto;

import com.mateuszmarcyk.walkthedog.notification.NotificationStatus;
import com.mateuszmarcyk.walkthedog.user.dto.UserDTO;
import com.mateuszmarcyk.walkthedog.walkeventinvitation.dto.WalkEventInvitationDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WalkEventInvitationNotificationDTO {

    private Long id;
    private UserDTO receiver;
    private NotificationStatus status;
    private WalkEventInvitationDTO invitation;

    public WalkEventInvitationNotificationDTO(UserDTO receiver, WalkEventInvitationDTO invitation) {
        this.receiver = receiver;
        this.status = NotificationStatus.UNREAD;
        this.invitation = invitation;
    }
}
