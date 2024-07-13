package com.mateuszmarcyk.walkthedog.walkevent;

public interface WalkEventService {

    WalkEvent findById(Long id);

    WalkEvent findByIdFetchParticipants(Long id);

    WalkEvent save(WalkEvent walkEvent);

    WalkEvent removeById(Long id);


}
