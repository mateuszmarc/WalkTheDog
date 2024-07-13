package com.mateuszmarcyk.walkthedog.friendrequestnotification;

import com.mateuszmarcyk.walkthedog.friendrequest.FriendRequest;
import com.mateuszmarcyk.walkthedog.notification.NotificationStatus;
import com.mateuszmarcyk.walkthedog.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "friend_request_notification")
public class FriendRequestNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receiver")
    private User receiver;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "friend_request")
    private FriendRequest friendRequest;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private NotificationStatus status;


    public FriendRequestNotification(User receiver, FriendRequest friendRequest, NotificationStatus status) {
        this.receiver = receiver;
        this.friendRequest = friendRequest;
        this.status = status;
    }
}
