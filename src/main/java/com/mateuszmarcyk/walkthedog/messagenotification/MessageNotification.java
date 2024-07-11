package com.mateuszmarcyk.walkthedog.messagenotification;

import com.mateuszmarcyk.walkthedog.message.Message;
import com.mateuszmarcyk.walkthedog.notification.Notification;
import com.mateuszmarcyk.walkthedog.notification.NotificationStatus;
import com.mateuszmarcyk.walkthedog.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
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

    @OneToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "message_id")
    private Message message;

    public MessageNotification(User receiver, NotificationStatus notificationStatus, Message message) {
        this.receiver = receiver;
        this.notificationStatus = notificationStatus;
        this.message = message;
    }
}
