package com.mateuszmarcyk.walkthedog.dog;

import com.mateuszmarcyk.walkthedog.dog.dto.DogDTO;
import com.mateuszmarcyk.walkthedog.user.User;
import com.mateuszmarcyk.walkthedog.user.UserService;
import com.mateuszmarcyk.walkthedog.user.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Slf4j
@Controller
public class DogController {

    private final DogServiceImpl dogService;
    private final UserService userService;


    @GetMapping("/users/dogs")
    public String showAllDogsForUser(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        UserDTO userDTO = userService.findByEmailJoinFetchDogs(userDetails.getUsername());

        log.info("{}", userDTO.getDogsDto());

        model.addAttribute("dogs", userDTO.getDogsDto());
        return "user-dogs";
    }

    @GetMapping("/users/dogs/{id}")
    public String showDogDetails(@PathVariable Long id, Model model) {
        DogDTO dogDTO =  dogService.findById(id);
        log.info("{}", dogDTO);
        model.addAttribute("dog", dogDTO);

        return "user-dog-info";
    }

    @GetMapping("/users/dogs/addDog")
    public String showAddDogForm(Model model) {
        model.addAttribute("dog", new DogDTO());

        return "user-dog-form";
    }

    @GetMapping("/users/dogs/edit/{dogId}")
    private String showEditDogForm(@PathVariable Long dogId, Model model) {

        DogDTO dogDTO = dogService.findById(dogId);

        log.info("{}", dogDTO);

        model.addAttribute("dog", dogDTO);

        return "user-dog-form";

    }

    @PostMapping("/users/dogs/dogForm")
    private String processDogForm(@Valid @ModelAttribute("dog") DogDTO dogDTO,
                                  @AuthenticationPrincipal UserDetails userDetails,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "user-dog-form";

        } else {
            String email = userDetails.getUsername();
            User dogOwner = userService.findUserByEmail(email);

             DogDTO saved = dogService.save(dogDTO, dogOwner);
             log.info("{}",saved);
            return "redirect:/users/dogs";
        }
    }



    @GetMapping("/users/dogs/delete/{dogId}")
    private String deleteDogById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long dogId) {

        User user = userService.findUserByEmail(userDetails.getUsername());

        dogService.deleteById(dogId, user);
        return "redirect:/users/dogs";
    }
}
