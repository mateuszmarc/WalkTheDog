package com.mateuszmarcyk.walkthedog.walkinvitationnotification;

public interface WalkEventInvitationNotificationService {

    WalkEventInvitationNotification findById(Long id);

    WalkEventInvitationNotification save(WalkEventInvitationNotification notification);

    WalkEventInvitationNotification deleteById(Long id);

    WalkEventInvitationNotification delete(WalkEventInvitationNotification notification);
}
