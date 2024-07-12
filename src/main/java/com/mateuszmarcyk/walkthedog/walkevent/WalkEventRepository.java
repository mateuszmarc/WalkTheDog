package com.mateuszmarcyk.walkthedog.walkevent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WalkEventRepository extends JpaRepository<WalkEvent, Long> {

    @Query(value = "SELECT w FROM WalkEvent w LEFT JOIN FETCH w.participants WHERE w.id=:walkEventId")
    Optional<WalkEvent> findByIdFetchParticipants(Long walkEventId);
}

