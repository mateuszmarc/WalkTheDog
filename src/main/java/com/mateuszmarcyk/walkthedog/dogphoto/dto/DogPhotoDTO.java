package com.mateuszmarcyk.walkthedog.dogphoto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DogPhotoDTO {

    private Long id;

    private String url;

    public DogPhotoDTO(String url) {
        this.url = url;
    }
}