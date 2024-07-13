package com.mateuszmarcyk.walkthedog.friendrequest;

import com.mateuszmarcyk.walkthedog.friendrequest.enums.RequestStatus;
import com.mateuszmarcyk.walkthedog.friendrequestnotification.FriendRequestNotification;
import com.mateuszmarcyk.walkthedog.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "friendRequest")
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "requestStatus")
    private RequestStatus requestStatus;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "responded_at")
    private LocalDateTime respondedAt;

    @Column(name = "message")
    private String message;

    @ManyToOne( cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @OneToOne(mappedBy = "friendRequest", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    private FriendRequestNotification friendRequestNotification;

}
