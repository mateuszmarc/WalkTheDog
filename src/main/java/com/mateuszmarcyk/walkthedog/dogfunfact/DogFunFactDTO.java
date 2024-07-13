package com.mateuszmarcyk.walkthedog.dogfunfact;

import lombok.Data;

import java.util.Date;

@Data
public class DogFunFactDTO {

    private Long id;


    private Date date;

    private String content;
}