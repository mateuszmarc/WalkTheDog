package com.mateuszmarcyk.walkthedog.walkevent;

import com.mateuszmarcyk.walkthedog.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
@ToString
@Entity
@Table(name = "walk_event")
public class WalkEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH
    }
    )
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

    public void addParticipant(User user) {
        participants.add(user);
    }

    public void removeParticipant(User user) {
        participants.remove(user);
    }


    @ManyToMany(mappedBy = "walkEventsUserIsInvitedFor", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    private List<User> invitedUsers = new ArrayList<>();
}
