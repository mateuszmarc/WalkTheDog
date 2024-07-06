package com.mateuszmarcyk.walkthedog.appconfiguration.converters;

import com.mateuszmarcyk.walkthedog.dogphoto.DogProfilePhoto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToProfilePhotoConverter implements Converter<String, DogProfilePhoto> {

    @Override
    public DogProfilePhoto convert(String urlPhotoPath) {
        return new DogProfilePhoto(urlPhotoPath);
    }
}