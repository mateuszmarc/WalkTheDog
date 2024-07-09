package com.mateuszmarcyk.walkthedog.appconfiguration.converters;

import com.mateuszmarcyk.walkthedog.friendrequest.enums.RequestStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToFriendRequestStatusConverter implements Converter<String, RequestStatus> {

    @Override
    public RequestStatus convert(String source) {
        return RequestStatus.valueOf(source);
    }
}
