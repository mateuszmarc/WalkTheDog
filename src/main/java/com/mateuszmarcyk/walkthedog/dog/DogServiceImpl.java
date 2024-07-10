package com.mateuszmarcyk.walkthedog.dog;

import com.mateuszmarcyk.walkthedog.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DogServiceImpl implements DogService{

    @Value("${resourceNotFoundExceptionMessage}")
    private String resourceNotFoundExceptionMessage;

    private final DogRepository dogRepository;

    @Override
    public Dog add(Dog dog) {
        log.info(" New dog {}", dog);
        return dogRepository.save(dog);
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
    public Dog delete(Long id) {
        return null;
    }
}
