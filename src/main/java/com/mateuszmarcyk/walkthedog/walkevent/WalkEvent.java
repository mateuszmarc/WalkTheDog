package com.mateuszmarcyk.walkthedog.walkevent;

import com.mateuszmarcyk.walkthedog.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "walk_event")
public class WalkEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToMany(mappedBy = "walkEvents")
    @Column(name = "participants")
    private List<User> participants;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private WalkStatus status;
}
