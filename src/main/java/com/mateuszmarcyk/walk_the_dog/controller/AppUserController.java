package com.mateuszmarcyk.walk_the_dog.controller;

import com.mateuszmarcyk.walk_the_dog.model.AppUser;
import com.mateuszmarcyk.walk_the_dog.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class AppUserController {

private final AppUserService appUserService;

    @GetMapping
    public List<AppUser> findAll() {
        return appUserService.getAll();
    }
}
