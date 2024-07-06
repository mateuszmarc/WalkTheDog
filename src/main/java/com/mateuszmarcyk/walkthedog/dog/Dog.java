package com.mateuszmarcyk.walkthedog.dog;

import com.mateuszmarcyk.walkthedog.dog.dogenums.ActivityLevel;
import com.mateuszmarcyk.walkthedog.dog.dogenums.Gender;
import com.mateuszmarcyk.walkthedog.dogphoto.DogPhoto;
import com.mateuszmarcyk.walkthedog.dogphoto.DogProfilePhoto;
import com.mateuszmarcyk.walkthedog.user.User;
import jakarta.persistence.*;
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

    @Column(name = "name")
    private String name;

    @Column(name = "breed")
    private String breed;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "spayed")
    private Boolean spayed;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;

    @Column(name = "preferred_activities")
    private String preferredActivities;

    @Column(name = "activity_level")
    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;

    @Column(name = "general_behaviour")
    private String generalBehaviour;

    @Column(name = "training_level")
    private int trainingLevel;

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

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;


    public void setOwner(User user) {
        this.owner = user;
        owner.addDog(this);
    }
}
