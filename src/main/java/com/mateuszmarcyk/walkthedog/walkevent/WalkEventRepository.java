package com.mateuszmarcyk.walkthedog.walkevent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkEventRepository extends JpaRepository<WalkEvent, Long> {
}
