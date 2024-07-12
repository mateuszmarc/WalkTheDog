package com.mateuszmarcyk.walkthedog.walkinvitationnotification;

import com.mateuszmarcyk.walkthedog.notification.Notification;
import com.mateuszmarcyk.walkthedog.notification.NotificationStatus;
import com.mateuszmarcyk.walkthedog.user.User;
import com.mateuszmarcyk.walkthedog.walkinvitation.WalkInvitation;
import jakarta.persistence.*;

@Entity
@Table(name = "walk_event_invitation_notification")
public class WalkEventInvitationNotification implements Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receiver")
    private User receiver;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private NotificationStatus status;

    @OneToOne
    @JoinColumn(name = "walk_invitation")
    private WalkInvitation invitation;
}
