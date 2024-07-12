package com.mateuszmarcyk.walkthedog.user;


import com.mateuszmarcyk.walkthedog.conversation.Conversation;
import com.mateuszmarcyk.walkthedog.dog.Dog;
import com.mateuszmarcyk.walkthedog.friendrequest.FriendRequest;
import com.mateuszmarcyk.walkthedog.friendrequestnotification.FriendRequestNotification;
import com.mateuszmarcyk.walkthedog.message.Message;
import com.mateuszmarcyk.walkthedog.messagenotification.MessageNotification;
import com.mateuszmarcyk.walkthedog.walkinvitationnotification.WalkEventInvitationNotification;
import com.mateuszmarcyk.walkthedog.walkevent.WalkEvent;
import com.mateuszmarcyk.walkthedog.walkeventinvitation.WalkEventInvitation;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
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

    @Column(name = "address")
    private String address;

    @Column(name = "bio")
    private String bio;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "app_user_role")
    private String appUserRole;

    @Column(name = "enabled")
    private Boolean enabled = false;

    @ToString.Exclude
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dog> dogs = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FriendRequest> sentFriendRequests = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FriendRequest> receivedFriendRequests = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> receivedMessages;

    @ManyToMany
    private List<User> friends = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinTable(name = "users_conversations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "conversation_id")
    )
    private List<Conversation> conversations = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "users_walk_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "walk_event_id")
    )
    private List<WalkEvent> walkEvents = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FriendRequestNotification> friendRequestNotifications = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalkEventInvitationNotification> walkEventInvitationNotifications = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessageNotification> messageNotifications = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalkEventInvitation> sentWalkEventInvitations = new ArrayList<>();


    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalkEventInvitation> receivedWalkEventInvitations = new ArrayList<>();


    @OneToMany(mappedBy = "creator", orphanRemoval = true)
    private List<WalkEvent> createdWalkEvents = new ArrayList<>();


    public User(String firstName, String lastName, String username, String email, String password, String userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.appUserRole = userRole;
    }

    public void addConversation(Conversation conversation) {
        conversations.add(conversation);
        conversation.getUsers().add(this);
    }

    public void removeConversation(Conversation conversation) {
        conversations.remove(conversation);
        conversation.getUsers().remove(this);
    }

    public void addDog(Dog dog) {
        dogs.add(dog);
        dog.setOwner(this);
    }

    public void removeDog(Dog dog) {
        dogs.remove(dog);
        dog.setOwner(null);
    }

    public void removeReceivedFriendRequest(FriendRequest friendRequest) {

        receivedFriendRequests.remove(friendRequest);
        removeFriendRequestNotification(friendRequest.getFriendRequestNotification());
        friendRequest.setReceiver(null);
    }

    public void removeSentFriendRequest(FriendRequest friendRequest) {

        sentFriendRequests.remove(friendRequest);
        friendRequest.setSender(null);
    }

    public void addReceivedFriendRequest(FriendRequest friendRequest) {

        receivedFriendRequests.add(friendRequest);

        FriendRequestNotification notification =friendRequest.getFriendRequestNotification();
        if (notification != null) {
            addFriendRequestNotification(notification);
        }
        friendRequest.setReceiver(this);
    }

    public void addSentFriendRequest(FriendRequest friendRequest) {

        sentFriendRequests.add(friendRequest);
        friendRequest.setSender(this);
    }

    public void addFriendRequestNotification(FriendRequestNotification notification) {

        friendRequestNotifications.add(notification);
        notification.setReceiver(this);
    }

    public void removeFriendRequestNotification(FriendRequestNotification friendRequestNotification) {

        friendRequestNotifications.remove(friendRequestNotification);
        friendRequestNotification.setReceiver(null);

    }

    public void addSentMessage(Message message) {

        sentMessages.add(message);

    }

    public void removeSentMessage(Message message) {
        sentMessages.remove(message);

    }

    public void addReceivedMessage(Message message) {

        receivedMessages.add(message);

        MessageNotification messageNotification = message.getMessageNotification();

        if (messageNotification != null) {
            addMessageNotification(messageNotification);
        }
    }

    public void removeReceivedMessage(Message message) {

        receivedMessages.remove(message);
        MessageNotification messageNotification = message.getMessageNotification();

        if (messageNotification != null) {
            removeMessageNotification(messageNotification);
        }
    }

    public void addMessageNotification(MessageNotification messageNotification) {

        messageNotifications.add(messageNotification);
        messageNotification.setReceiver(this);
    }

    public void removeMessageNotification(MessageNotification messageNotification) {
        messageNotifications.remove(messageNotification);
        messageNotification.setReceiver(null);
    }

    public void addCreatedWAlkEvent(WalkEvent walkEvent) {

        createdWalkEvents.add(walkEvent);
        walkEvent.setCreator(this);
    }

    public void removeCreatedWalkEvent(WalkEvent walkEvent) {

        createdWalkEvents.remove(walkEvent);
        walkEvent.setCreator(null);
    }

    public void addWalkEvent(WalkEvent walkEvent) {

        walkEvents.add(walkEvent);
        walkEvent.addParticipant(this);
    }

    public void removeWalkEvent(WalkEvent walkEvent) {

        walkEvents.remove(walkEvent);
        walkEvent.removeParticipant(this);
    }

    public void addSentWalkInvitation(WalkEventInvitation walkEventInvitation) {
        sentWalkEventInvitations.add(walkEventInvitation);
    }

    public void removeSentWalkInvitation(WalkEventInvitation walkEventInvitation) {
        receivedWalkEventInvitations.remove(walkEventInvitation);
    }

    public void addReceivedWalkInvitation(WalkEventInvitation walkEventInvitation) {
        receivedWalkEventInvitations.add(walkEventInvitation);
    }

    public void removeReceivedWalkInvitation(WalkEventInvitation walkEventInvitation) {
        receivedWalkEventInvitations.remove(walkEventInvitation);
    }

    public void addWalkInvitationNotification(WalkEventInvitationNotification invitationNotification) {
        walkEventInvitationNotifications.add(invitationNotification);
    }

    public void removeWalkInvitationNotification(WalkEventInvitationNotification invitationNotification) {
        walkEventInvitationNotifications.remove(invitationNotification);
    }

}
