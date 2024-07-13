package com.mateuszmarcyk.walkthedog.dog;

import com.mateuszmarcyk.walkthedog.dog.dto.DogDTO;
import com.mateuszmarcyk.walkthedog.dog.dto.DogMapper;
import com.mateuszmarcyk.walkthedog.exception.ResourceNotFoundException;
import com.mateuszmarcyk.walkthedog.user.User;
import com.mateuszmarcyk.walkthedog.user.UserRepository;
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
public class DogServiceImpl implements DogService {

    @Value("${resourceNotFoundExceptionMessage}")
    private String resourceNotFoundExceptionMessage;

    private final UserRepository userRepository;
    private final DogRepository dogRepository;
    private final UserService userService;
    private final DogMapper dogMapper;



    @Override
    public DogDTO save(DogDTO dogDto, User user) {

        log.info("{}", dogDto);
        Dog dog = dogMapper.toEntity(dogDto);

        log.info(" New dog {}", dog);

        user.addDog(dog);
        dog.setOwner(user);

//        Dog savedDog = dogRepository.save(dog);
        userRepository.save(user);

        DogDTO dogDTO =  dogMapper.toDTO(dog);
        log.info("{}", dogDTO.getOwner());
        return dogDTO;
    }

    @Override
    public DogDTO findById(Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);

        return optionalDog.map(dogMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("Dog", id)));
    }

    @Override
    public List<DogDTO> getAll() {
        List<Dog> dogs = dogRepository.findAll();

        return dogs.stream().map(dogMapper::toDTO).toList();
    }

    @Override
    public DogDTO deleteById(Long id, User user) {
        
        Optional<Dog> dog = dogRepository.findById(id);

        return dog.map(foundDog -> {
                    user.removeDog(foundDog);
                    foundDog.setOwner(null);

                    userRepository.save(user);
                    
//                    dogRepository.delete(foundDog);
                    return dogMapper.toDTO(foundDog);
                })
                .orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("Dog", id)));
    }
}
