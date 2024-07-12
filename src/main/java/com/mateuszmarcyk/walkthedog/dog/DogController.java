package com.mateuszmarcyk.walkthedog.dog;

import com.mateuszmarcyk.walkthedog.user.User;
import com.mateuszmarcyk.walkthedog.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@Controller
public class DogController {

    private final DogServiceImpl dogService;
    private final UserService userService;


    @GetMapping("/users/dogs")
    public String showAllDogsForUser(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        User user = userService.findByEmailJoinFetchDogs(userDetails.getUsername());
        model.addAttribute("dogs", user.getDogs());
        return "user-dogs";
    }

    @GetMapping("/users/dogs/{id}")
    public String showDogDetails(@PathVariable Long id, Model model) {
        Dog dog =  dogService.findById(id);
        log.info("{}", dog);
        model.addAttribute("dog", dog);

        return "user-dog-info";
    }

    @GetMapping("/users/dogs/addDog")
    public String showAddDogForm(Model model) {
        model.addAttribute("dog", new Dog());

        return "user-dog-form";
    }

    @GetMapping("/users/dogs/edit/{dogId}")
    private String showEditDogForm(@PathVariable Long dogId, Model model) {

        Dog dog = dogService.findById(dogId);

        model.addAttribute("dog", dog);

        return "user-dog-form";

    }

    @PostMapping("/users/dogs/dogForm")
    private String processDogForm(@Valid @ModelAttribute("dog") Dog dog,
                                  @AuthenticationPrincipal UserDetails userDetails,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "index";
        } else {
            String email = userDetails.getUsername();
            User dogOwner = userService.findByEmail(email);

            dogService.save(dog, dogOwner);
            return "redirect:/users/dogs";
        }
    }



    @GetMapping("/users/dogs/delete/{dogId}")
    private String deleteDogById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long dogId) {

        User user = userService.findByEmail(userDetails.getUsername());

        dogService.deleteById(dogId, user);
        return "redirect:/users/dogs";
    }
}
