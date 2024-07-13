package com.mateuszmarcyk.walkthedog.walkevent;


import com.mateuszmarcyk.walkthedog.exception.ResourceNotFoundException;
import com.mateuszmarcyk.walkthedog.user.User;
import com.mateuszmarcyk.walkthedog.user.UserRepository;
import com.mateuszmarcyk.walkthedog.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class WalkEventServiceImpl implements WalkEventService {

    private final UserRepository userRepository;
    @Value("${resourceNotFoundExceptionMessage}")
    private String resourceNotFoundExceptionMessage;


    private final WalkEventRepository walkEventRepository;
    private final UserService userService;

    @Override
    public WalkEvent findById(Long id) {
        Optional<WalkEvent> walkEvent = walkEventRepository.findById(id);

        return walkEvent.orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("Walk Event", id)));
    }

    @Override
    public WalkEvent findByIdFetchParticipants(Long id) {
        Optional<WalkEvent> walkEvent = walkEventRepository.findByIdFetchParticipants(id);

        return walkEvent.orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("Walk Event", id)));
    }

    @Override
    public WalkEvent save(WalkEvent walkEvent) {
        User creator = walkEvent.getCreator();
        List<User> participants = walkEvent.getParticipants();

        creator.addCreatedWAlkEvent(walkEvent);
        userRepository.save(creator);

        participants.forEach(participant -> {
            participant.addWalkEvent(walkEvent);
            userRepository.save(participant);
        });

        return walkEventRepository.save(walkEvent);
    }

    @Override
    public WalkEvent removeById(Long id) {
        WalkEvent walkEvent = findByIdFetchParticipants(id);


        User creator = walkEvent.getCreator();
        List<User> participants = walkEvent.getParticipants();

        creator.removeCreatedWalkEvent(walkEvent);

                    participants.forEach(participant -> {

                        participant.removeWalkEvent(walkEvent);
                        userRepository.save(participant);
                    });

        walkEventRepository.delete(walkEvent);
        return walkEvent;

    }

}
