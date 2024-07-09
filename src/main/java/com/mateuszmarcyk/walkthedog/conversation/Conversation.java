package com.mateuszmarcyk.walkthedog.conversation;

import com.mateuszmarcyk.walkthedog.message.Message;
import com.mateuszmarcyk.walkthedog.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "conversation")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToMany(mappedBy = "conversations")
    private List<User> users;

    @Column(name = "created_ad")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.MERGE)
    private List<Message> messages;
}
