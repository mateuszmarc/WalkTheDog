package com.mateuszmarcyk.walkthedog.dog;

import com.mateuszmarcyk.walkthedog.exception.ResourceNotFoundException;
import com.mateuszmarcyk.walkthedog.user.User;
import com.mateuszmarcyk.walkthedog.user.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public Dog save(Dog dog) {
        log.info(" New dog {}", dog);

        dogRepository.save(dog);

        User user = dog.getOwner();

        if (user != null) {
            List<Dog> ownerDogs = user.getDogs();
            if (!ownerDogs.contains(dog)) {
                ownerDogs.add(dog);
                userRepository.save(user);
            }
        }
        return dog;
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
    public Dog deleteById(Long id) {
        Optional<Dog> dog = dogRepository.findById(id);

        return dog.map(foundDog -> {
                    User owner = foundDog.getOwner();
                    foundDog.getOwner().removeDog(foundDog);

                    userRepository.save(owner);
                    dogRepository.delete(foundDog);
                    return foundDog;
                })
                .orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("Dog", id)));
    }
}
