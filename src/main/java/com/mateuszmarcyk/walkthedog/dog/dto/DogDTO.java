package com.mateuszmarcyk.walkthedog.dog.dto;

import com.mateuszmarcyk.walkthedog.dog.dogenums.ActivityLevel;
import com.mateuszmarcyk.walkthedog.dog.dogenums.Gender;
import com.mateuszmarcyk.walkthedog.dogphoto.dto.DogPhotoDTO;
import com.mateuszmarcyk.walkthedog.dogphoto.dto.DogProfilePhotoDTO;
import com.mateuszmarcyk.walkthedog.user.dto.UserDTO;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class DogDTO {

    private Long id;

    @NotNull(message = "Imię jest wymagane")
    @Size(min = 1, message = "Imię jest wymagane")
    private String name;

    private String breed;

    @NotNull(message = "Ta informacja jest wymagana")
    @Past(message = "Data urodzenia musi być z przeszłości")
    private LocalDate dateOfBirth;

    @NotNull(message = "Płeć jest wymagana")
    private Gender gender;

    @NotNull(message = "Ta informacja jest wymagana")
    private Boolean spayed;

    @NotNull(message = "Ta informacja jest wymagana")
    @Min(value = 1, message = "Wpisz poprawną wagę, większą niż 1kg")
    @Max(value = 80, message = "Wpisz poprawną wagę, mniejsza niż 80kg")
    private double weight;

    @NotNull(message = "Ta informacja jest wymagana")
    @Min(value = 15, message = "Wpisz poprawną wagę, większą niż 5cm")
    private double height;

    private String preferredActivities;

    @NotNull(message = "Ta informacja jest wymagana")
    private ActivityLevel activityLevel;

    private String generalBehaviour;

    @NotNull(message = "Ta informacja jest wymagana")
    private int trainingLevel;

    @NotNull(message = "Ta informacja jest wymagana")
    private int socializationLevel;


    private String behavioralIssues;

    private String triggers;

    private String microChipNumber;

    private DogProfilePhotoDTO profilePhoto;

    private List<DogPhotoDTO> photos;

    private UserDTO owner;
}

