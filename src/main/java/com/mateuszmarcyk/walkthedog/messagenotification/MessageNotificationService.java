package com.mateuszmarcyk.walkthedog.messagenotification;

public interface MessageNotificationService {

    MessageNotification save(MessageNotification messageNotification);

    MessageNotification delete(MessageNotification messageNotification);
}
