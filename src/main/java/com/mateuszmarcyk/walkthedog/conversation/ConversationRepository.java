package com.mateuszmarcyk.walkthedog.conversation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query(value = "SELECT c FROM Conversation c LEFT JOIN FETCH c.messages WHERE c.id=:conversationId")
    Optional<Conversation> findByIdFetchMessages(Long conversationId);
}
