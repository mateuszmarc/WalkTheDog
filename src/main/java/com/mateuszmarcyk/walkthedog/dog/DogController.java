package com.mateuszmarcyk.walkthedog.dog;

import com.mateuszmarcyk.walkthedog.user.User;
import com.mateuszmarcyk.walkthedog.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/users/{userId}/dogs")
public class DogController {

    private final DogServiceImpl dogService;
    private final UserService userService;

    @GetMapping
    @ResponseBody
    public List<Dog> findAllDogsForUser(@PathVariable Long userId) {
        User user = userService.findById(userId);
        log.info("{}", user.getDogs());
        return user.getDogs();
    }

    @GetMapping("/{dogId}")
    @ResponseBody
    public Dog getDogDetails(@PathVariable Long userId, @PathVariable Long dogId) {

        User user = userService.findById(userId);

        return dogService.findById(dogId);
    }

    @GetMapping("/addDog")
    public String showAddDogForm(@PathVariable Long userId, @ModelAttribute Dog dog, HttpServletRequest request) {

        User user = userService.findById(userId);
        request.setAttribute("userId", userId);

        return "dog-form";
    }

    @PostMapping("/addDog")
    @ResponseBody
    private String processDogForm(@PathVariable Long userId, Dog dog) {

        User dogOwner = userService.findById(userId);
        dog.setOwner(dogOwner);

        dogService.add(dog);
        return "Dog added sucessfully";
    }

    @SuppressWarnings("unused")
    @GetMapping("/editDog/{dogId}")
    private String showEditDogForm(@PathVariable Long userId, @PathVariable Long dogId, Model model) {

        User user = userService.findById(userId);

        Dog dog = dogService.findById(dogId);

        model.addAttribute("dog", dog);

        return "dog-form";

    }

    @SuppressWarnings("unused")
    @DeleteMapping("/deleteDog/{dogId}")
    @ResponseBody
    private Dog deleteDogById(@PathVariable Long userId, @PathVariable Long dogId) {

        User user = userService.findById(userId);

        return dogService.delete(dogId);
    }
}
