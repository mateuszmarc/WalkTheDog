package com.mateuszmarcyk.walkthedog.dog;

import com.mateuszmarcyk.walkthedog.user.User;
import com.mateuszmarcyk.walkthedog.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@Controller
public class DogController {

    private final DogServiceImpl dogService;
    private final UserService userService;


    @GetMapping("/users/dogs")
    public String findAllDogsForUser(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        User user = userService.findUserByEmailJoinFetchDogs(userDetails.getUsername());
        log.info("{}", user.getDogs());
        model.addAttribute("dogs", user.getDogs());
        return "user-dogs";
    }

    @GetMapping("/{dogId}")
    @ResponseBody
    public Dog getDogDetails(@PathVariable Long dogId) {
        return dogService.findById(dogId);
    }

    @GetMapping("/addDog")
    public String showAddDogForm(@ModelAttribute Dog dog) {
        return "dog-form";
    }

    @PostMapping("/addDog")
    private String processDogForm( Dog dog) {

//        String email = userDetails.getUsername();
//
//        User dogOwner = userService.findByEmail(email);
//        dog.setOwner(dogOwner);

        dogService.add(dog);
        return "redirect:/users/dogs";
    }


    @GetMapping("/editDog/{dogId}")
    private String showEditDogForm(@PathVariable Long dogId, Model model) {

        Dog dog = dogService.findById(dogId);

        model.addAttribute("dog", dog);

        return "dog-form";

    }

    @DeleteMapping("/deleteDog/{dogId}")
    private String deleteDogById( @PathVariable Long dogId) {

//        User user = userService.findUserByEmail(userDetails.getUsername());

        dogService.delete(dogId);
        return "redirect:/users/dogs";
    }
}
