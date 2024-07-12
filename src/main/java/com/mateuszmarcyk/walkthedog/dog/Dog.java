package com.mateuszmarcyk.walkthedog.dog;

import com.mateuszmarcyk.walkthedog.dog.dogenums.ActivityLevel;
import com.mateuszmarcyk.walkthedog.dog.dogenums.Gender;
import com.mateuszmarcyk.walkthedog.dogphoto.DogPhoto;
import com.mateuszmarcyk.walkthedog.dogphoto.DogProfilePhoto;
import com.mateuszmarcyk.walkthedog.user.User;
import jakarta.persistence.*;
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
@Entity
@Table(name = "dog")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Imię jest wymagane")
    @Size(min = 1, message = "Imię jest wymagane")
    @Column(name = "name")
    private String name;

    @Column(name = "breed")
    private String breed;

    @Past(message = "Data urodzenia musi być z przeszłości")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @NotNull(message = "Płeć jest wymagana")
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "Ta informacja jest wymagana")
    @Column(name = "spayed")
    private Boolean spayed;

    @Min(value = 1, message = "Wpisz poprawną wagę, większą niż 1kg")
    @Max(value = 80, message = "Wpisz poprawną wagę, mniejsza niż 80kg")
    @Column(name = "weight")
    private double weight;

    @Min(value = 15, message = "Wpisz poprawną wagę, większą niż 5cm")
    @Column(name = "height")
    private double height;

    @Column(name = "preferred_activities")
    private String preferredActivities;

    @NotNull(message = "Ta informacja jest wymagana")
    @Column(name = "activity_level")
    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;

    @Column(name = "general_behaviour")
    private String generalBehaviour;

    @NotNull(message = "Ta informacja jest wymagana")
    @Column(name = "training_level")
    private int trainingLevel;

    @NotNull(message = "Ta informacja jest wymagana")
    @Column(name = "socialization_level")
    private int socializationLevel;

    @Column(name = "behavioral_issues")
    private String behavioralIssues;

    @Column(name = "triggers")
    private String triggers;

    @Column(name = "microchip_number")
    private String microChipNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_photo_id")
    private DogProfilePhoto profilePhoto;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DogPhoto> photos;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "owner_id")
    private User owner;


}
