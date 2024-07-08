package com.mateuszmarcyk.walkthedog.friendrequest;

import com.mateuszmarcyk.walkthedog.friendrequest.enums.FriendRequestStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "friendRequest")
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "requestStatus")
    private FriendRequestStatus requestStatus;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "responded_at")
    private LocalDateTime respondedAt;

    @Column(name = "message")
    private String message;

}
