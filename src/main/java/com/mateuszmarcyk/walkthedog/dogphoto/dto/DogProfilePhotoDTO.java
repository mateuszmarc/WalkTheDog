package com.mateuszmarcyk.walkthedog.dogphoto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DogProfilePhotoDTO {

    private Long id;

    @NotBlank(message = "URL is required")
    private String url;

    public DogProfilePhotoDTO(String url) {
        this.url = url;
    }
}

