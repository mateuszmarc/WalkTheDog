package com.mateuszmarcyk.walkthedog.walkinvitation;

import com.mateuszmarcyk.walkthedog.friendrequest.enums.RequestStatus;
import com.mateuszmarcyk.walkthedog.user.User;
import com.mateuszmarcyk.walkthedog.walkevent.WalkEvent;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "walk_invitation")
public class WalkInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RequestStatus status;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "responded_at")
    private LocalDateTime respondedAt;

    @OneToOne
    @JoinColumn(name = "walk_event")
    private WalkEvent walkEvent;

    @ManyToOne
    @JoinColumn(name = "sender")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver")
    private User receiver;

}
