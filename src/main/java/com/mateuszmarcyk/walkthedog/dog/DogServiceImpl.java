package com.mateuszmarcyk.walkthedog.dog;

import com.mateuszmarcyk.walkthedog.exception.ResourceNotFoundException;
import com.mateuszmarcyk.walkthedog.user.User;
import com.mateuszmarcyk.walkthedog.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class DogServiceImpl implements DogService{

    @Value("${resourceNotFoundExceptionMessage}")
    private String resourceNotFoundExceptionMessage;

    private final DogRepository dogRepository;
    private final UserService userService;


    @Override
    public Dog save(Dog dog) {

        return dogRepository.save(dog);
    }

    @Override
    public Dog save(Dog dog, User user) {

        log.info(" New dog {}", dog);

        if (user != null) {
            user.addDog(dog);
        }

        dogRepository.save(dog);
        userService.save(user);

        return save(dog);
    }

    @Override
    public Dog findById(Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);

        return optionalDog.orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("Dog", id)));
    }

    @Override
    public List<Dog> getAll() {
        return List.of();
    }

    @Override
    public Dog deleteById(Long id, User user) {
        
        Optional<Dog> dog = dogRepository.findById(id);

        return dog.map(foundDog -> {
                    user.removeDog(foundDog);
                    foundDog.setOwner(null);

                    userService.save(user);
                    
                    dogRepository.delete(foundDog);
                    return foundDog;
                })
                .orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("Dog", id)));
    }
}
