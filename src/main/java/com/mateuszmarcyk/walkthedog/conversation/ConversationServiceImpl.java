package com.mateuszmarcyk.walkthedog.conversation;

import com.mateuszmarcyk.walkthedog.conversation.dto.ConversationDTO;
import com.mateuszmarcyk.walkthedog.exception.ResourceNotFoundException;
import com.mateuszmarcyk.walkthedog.user.User;
import com.mateuszmarcyk.walkthedog.user.UserRepository;
import com.mateuszmarcyk.walkthedog.user.UserServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ConversationServiceImpl implements ConversationService {

    private final UserRepository userRepository;
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
                userRepository.save(user);
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
                        userRepository.save(user);
                    });
                    conversationRepository.deleteById(id);
                    return foundConversation;
                })
                .orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("Conversation", id)));
    }

    @Override
    public List<ConversationDTO> getConversationDtos(User user) {
        return null;
    }

//    @Override
//    public List<ConversationDto> getConversationDtos(User user) {
//        return user.getConversations().stream().map(conversation ->
//        {
//            User firstUser = conversation.getUsers().get(0);
//            User secondUser = conversation.getUsers().get(1);
//
//            if (!conversation.getUsers().get(0).equals(user)) {
//                firstUser = conversation.getUsers().get(1);
//                secondUser = conversation.getUsers().get(0);
//            }
//
//            return new ConversationDto(
//                    conversation.getId(),
//                    firstUser,
//                    secondUser
//            );
//        }).toList();
//    }
}
