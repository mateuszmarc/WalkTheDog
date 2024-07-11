package com.mateuszmarcyk.walkthedog.conversation;

import com.mateuszmarcyk.walkthedog.message.Message;
import com.mateuszmarcyk.walkthedog.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "conversation")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToMany(mappedBy = "conversations", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    private List<User> users;

    @Column(name = "created_ad")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private List<Message> messages;


    public void addUser(User user) {
        users.add(user);
        user.getConversations().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getConversations().remove(this);
    }

    public void addMessage(Message message) {
        messages.add(message);
        message.setConversation(this);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
        message.setConversation(null);
    }
}
