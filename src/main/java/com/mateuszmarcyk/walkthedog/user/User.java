package com.mateuszmarcyk.walkthedog.user;


import com.mateuszmarcyk.walkthedog.conversation.Conversation;
import com.mateuszmarcyk.walkthedog.dog.Dog;
import com.mateuszmarcyk.walkthedog.friendrequest.FriendRequest;
import com.mateuszmarcyk.walkthedog.notification.FriendRequestNotification;
import com.mateuszmarcyk.walkthedog.notification.MessageNotification;
import com.mateuszmarcyk.walkthedog.notification.WalkEventInvitationNotification;
import com.mateuszmarcyk.walkthedog.walkevent.WalkEvent;
import com.mateuszmarcyk.walkthedog.walkinvitation.WalkInvitation;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "username", unique = true)
    private String username;

    @NaturalId(mutable = true)
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "bio")
    private String bio;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "app_user_role")
    private String appUserRole;

    @Column(name = "enabled")
    private Boolean enabled = false;

    @ToString.Exclude
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Dog> dogs;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    @Column(name = "sent_friend_request")
    private List<FriendRequest> sentFriendRequests;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    @Column(name = "received_friend_request")
    private List<FriendRequest> receivedFriendRequests;

    @ManyToMany
    private List<User> friends;

    @ManyToMany
    @JoinTable(name = "users_conversations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "conversation_id")
    )
    private List<Conversation> conversations;

    @ManyToMany
    @JoinTable(name = "users_walk_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "walk_event_id")
    )
    private List<WalkEvent> walkEvents;

    public void addDog(Dog dog) {
        dogs.add(dog);
    }

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<FriendRequestNotification> friendRequestNotifications;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<WalkEventInvitationNotification> walkEventInvitationNotifications;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<MessageNotification> messageNotifications;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<WalkInvitation> walkInvitations;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<WalkInvitation> sentWalkInvitations;


    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<WalkInvitation> receivedWalkInvitations;
}
