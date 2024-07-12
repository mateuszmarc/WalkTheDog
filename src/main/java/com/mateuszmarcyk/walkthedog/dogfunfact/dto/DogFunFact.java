package com.mateuszmarcyk.walkthedog.dogfunfact.dto;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "dog_fun_fact")
public class DogFunFact {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "content")
    private String content;
}
