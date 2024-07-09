package com.mateuszmarcyk.walkthedog.appconfiguration.converters;

import com.mateuszmarcyk.walkthedog.notification.NotificationStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class StringToNotificationStatusConverter implements Converter<String, NotificationStatus> {

    @Override
    public NotificationStatus convert(String source) {
        return NotificationStatus.valueOf(source);
    }
}