package com.mateuszmarcyk.walkthedog.appconfiguration.converters;

import com.mateuszmarcyk.walkthedog.friendrequest.enums.FriendRequestStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToFriendRequestStatusConverter implements Converter<String, FriendRequestStatus> {

    @Override
    public FriendRequestStatus convert(String source) {
        return FriendRequestStatus.valueOf(source);
    }
}
