package com.mateuszmarcyk.walkthedog.conversation;

public interface ConversationService {

    Conversation findById(Long id);

    Conversation findByIdFetchMessages(Long id);

    Conversation save(Conversation conversation);

    Conversation deleteById(Long id);

}
