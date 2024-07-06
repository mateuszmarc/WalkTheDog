package com.mateuszmarcyk.walkthedog.appconfiguration.converters;

import com.mateuszmarcyk.walkthedog.dogphoto.DogPhoto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class StringToPhotoConverter implements Converter<String, DogPhoto> {

    @Override
    public DogPhoto convert(String urlPhotoPath) {
        return new DogPhoto(urlPhotoPath);
    }
}
