package com.mateuszmarcyk.walkthedog.appconfiguration.converters;

import com.mateuszmarcyk.walkthedog.dog.dogenums.ActivityLevel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToActivityLevelConverter implements Converter<String, ActivityLevel> {

    @Override
    public ActivityLevel convert(String activity) {
        return ActivityLevel.valueOf(activity.toUpperCase());
    }
}