package com.mateuszmarcyk.walkthedog.friendrequestnotification;

public interface FriendRequestNotificationService {

    FriendRequestNotification save(FriendRequestNotification friendRequestNotification);

    FriendRequestNotification delete(FriendRequestNotification notification);
}
