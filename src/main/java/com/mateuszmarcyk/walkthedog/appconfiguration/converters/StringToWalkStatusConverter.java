package com.mateuszmarcyk.walkthedog.appconfiguration.converters;

import com.mateuszmarcyk.walkthedog.walkevent.WalkStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToWalkStatusConverter implements Converter<String, WalkStatus> {

    @Override
    public WalkStatus convert(String source) {
        return WalkStatus.valueOf(source);
    }
}