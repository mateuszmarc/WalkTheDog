package com.mateuszmarcyk.walkthedog.conversation;

import com.mateuszmarcyk.walkthedog.conversation.dto.ConversationDTO;
import com.mateuszmarcyk.walkthedog.user.User;

import java.util.List;

public interface ConversationService {

    Conversation findById(Long id);

    Conversation findByIdFetchMessages(Long id);

    Conversation save(Conversation conversation);

    Conversation deleteById(Long id);

    public List<ConversationDTO> getConversationDtos(User user);

}
