package com.mateuszmarcyk.walkthedog.walkevent;


import com.mateuszmarcyk.walkthedog.exception.ResourceNotFoundException;
import com.mateuszmarcyk.walkthedog.user.User;
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

    @Value("${resourceNotFoundExceptionMessage}")
    private String resourceNotFoundExceptionMessage;


    private final WalkEventRepository walkEventRepository;
    private final UserService userService;

    @Override
    public WalkEvent save(WalkEvent walkEvent) {
        User creator = walkEvent.getCreator();
        List<User> participants = walkEvent.getParticipants();

        creator.addCreatedWAlkEvent(walkEvent);
        userService.save(creator);

        participants.forEach(participant -> {
            participant.addWalkEvent(walkEvent);
            userService.save(participant);
        });

        return walkEventRepository.save(walkEvent);
    }

    @Override
    public WalkEvent removeById(Long id) {
        Optional<WalkEvent> walkEvent = walkEventRepository.findById(id);

        return walkEvent.map(foundWalkEvent -> {

                    User creator = foundWalkEvent.getCreator();
                    List<User> participants = foundWalkEvent.getParticipants();

                    creator.removeCreatedWalkEvent(foundWalkEvent);

                    participants.forEach(participant -> {

                        participant.removeWalkEvent(foundWalkEvent);
                        userService.save(participant);
                    });

                    walkEventRepository.delete(foundWalkEvent);
                    return foundWalkEvent;
                })
                .orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("Walk Event", id)));

    }

    @Override
    public WalkEvent remove(WalkEvent walkEvent) {
        return null;
    }
}
