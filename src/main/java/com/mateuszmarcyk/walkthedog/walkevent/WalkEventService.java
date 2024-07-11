package com.mateuszmarcyk.walkthedog.walkevent;

public interface WalkEventService {

    WalkEvent save(WalkEvent walkEvent);

    WalkEvent removeById(Long id);

    WalkEvent remove(WalkEvent walkEvent);
}
