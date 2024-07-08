package com.mateuszmarcyk.walkthedog.user;


import com.mateuszmarcyk.walkthedog.dog.Dog;
import com.mateuszmarcyk.walkthedog.friendrequest.FriendRequest;
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

    public void addDog(Dog dog) {
        dogs.add(dog);
    }

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "sent_friend_request")
    private List<FriendRequest> sentFriendRequests;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "received_friend_request")
    private List<FriendRequest> receivedFriendRequests;

    @ManyToMany
    private List<User> friends;

}
