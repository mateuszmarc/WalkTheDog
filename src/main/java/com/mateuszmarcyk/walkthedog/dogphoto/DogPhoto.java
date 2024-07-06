package com.mateuszmarcyk.walkthedog.dogphoto;

import jakarta.persistence.*;

@Entity
@Table(name = "photo")
public class DogPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "photo_type")
    private PhotoType photoType;
}
