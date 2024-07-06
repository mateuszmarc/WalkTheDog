package com.mateuszmarcyk.walkthedog.appconfiguration.converters;

import com.mateuszmarcyk.walkthedog.dog.dogenums.Gender;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToGenderConverter implements Converter<String, Gender> {

    @Override
    public Gender convert(String genderType) {
        return Gender.valueOf(genderType.toUpperCase());
    }
}
