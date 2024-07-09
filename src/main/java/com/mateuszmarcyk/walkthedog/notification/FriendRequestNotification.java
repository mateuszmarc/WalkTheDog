package com.mateuszmarcyk.walkthedog.notification;

import com.mateuszmarcyk.walkthedog.friendrequest.FriendRequest;
import com.mateuszmarcyk.walkthedog.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "friend_request_notification")
public class FriendRequestNotification implements Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receiver")
    private User receiver;

    @OneToOne
    @JoinColumn(name = "friend_request")
    private FriendRequest friendRequest;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private NotificationStatus status;



}
