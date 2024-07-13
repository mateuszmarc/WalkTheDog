package com.mateuszmarcyk.walkthedog.walkinvitationnotification;

import com.mateuszmarcyk.walkthedog.notification.NotificationStatus;
import com.mateuszmarcyk.walkthedog.user.User;
import com.mateuszmarcyk.walkthedog.walkeventinvitation.WalkEventInvitation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "walk_event_invitation_notification")
public class WalkEventInvitationNotification {

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
    private WalkEventInvitation invitation;

    public WalkEventInvitationNotification(User receiver, WalkEventInvitation invitation) {
        this.receiver = receiver;
        this.status = NotificationStatus.UNREAD;
        this.invitation = invitation;
    }
}
