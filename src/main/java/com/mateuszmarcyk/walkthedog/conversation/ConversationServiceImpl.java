package com.mateuszmarcyk.walkthedog.conversation;

import com.mateuszmarcyk.walkthedog.exception.ResourceNotFoundException;
import com.mateuszmarcyk.walkthedog.user.UserServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ConversationServiceImpl implements ConversationService {

    @Value("${resourceNotFoundExceptionMessage}")
    private String resourceNotFoundExceptionMessage;

    private final UserServiceImpl userService;
    private final ConversationRepository conversationRepository;

    @Override
    public Conversation findById(Long id) {
        Optional<Conversation> conversation = conversationRepository.findById(id);

        return conversation.orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("Conversation", id)));
    }

    @Override
    public Conversation findByIdFetchMessages(Long id) {
        Optional<Conversation> conversation = conversationRepository.findById(id);

        return conversation.orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("Conversation", id)));
    }

    @Override
    public Conversation save(Conversation conversation) {
        Conversation savedConversation = conversationRepository.save(conversation);

        conversation.getUsers().forEach(user -> {
            if (!user.getConversations().contains(conversation)) {
                user.addConversation(conversation);
                userService.save(user);
            }
        });

        return savedConversation;
    }

    @Override
    public Conversation deleteById(Long id) {

        Optional<Conversation> conversation = conversationRepository.findById(id);

        return conversation.map(foundConversation -> {

                    foundConversation.getUsers().forEach(user -> {
                        user.removeConversation(foundConversation);
                        userService.save(user);
                    });
                    conversationRepository.deleteById(id);
                    return foundConversation;
                })
                .orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("Conversation", id)));
    }
}
