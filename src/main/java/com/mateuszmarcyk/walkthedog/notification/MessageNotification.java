package com.mateuszmarcyk.walkthedog.notification;

import com.mateuszmarcyk.walkthedog.message.Message;
import com.mateuszmarcyk.walkthedog.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "notification")
public class MessageNotification implements Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private NotificationStatus notificationStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "message")
    private Message message;

}
