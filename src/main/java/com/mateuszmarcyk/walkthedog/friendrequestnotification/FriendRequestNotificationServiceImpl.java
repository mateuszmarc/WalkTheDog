package com.mateuszmarcyk.walkthedog.friendrequestnotification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class FriendRequestNotificationServiceImpl implements FriendRequestNotificationService {

    private FriendRequestNotificationRepository friendRequestNotificationRepository;

    @Override
    public FriendRequestNotification save(FriendRequestNotification friendRequestNotification) {
        return friendRequestNotificationRepository.save(friendRequestNotification);
    }

    @Override
    public FriendRequestNotification delete(FriendRequestNotification notification) {

        friendRequestNotificationRepository.delete(notification);
        return notification;
    }


}
