package com.mateuszmarcyk.walkthedog.walkevent;


import com.mateuszmarcyk.walkthedog.user.User;
import com.mateuszmarcyk.walkthedog.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class WalkEventServiceImpl implements WalkEventService {

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
        return null;
    }

    @Override
    public WalkEvent remove(WalkEvent walkEvent) {
        return null;
    }
}
