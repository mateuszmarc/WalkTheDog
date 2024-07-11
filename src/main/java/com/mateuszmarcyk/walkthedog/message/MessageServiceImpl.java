package com.mateuszmarcyk.walkthedog.message;

import com.mateuszmarcyk.walkthedog.conversation.Conversation;
import com.mateuszmarcyk.walkthedog.conversation.ConversationRepository;
import com.mateuszmarcyk.walkthedog.exception.ResourceNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Setter
@Getter
@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MessageServiceImpl implements MessageService {

    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private String resourceNotFoundExceptionMessage;

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;

    @Override
    public Message save(Message message) {

        Conversation conversation = message.getConversation();

        if (conversation != null) {

            messageRepository.save(message);

            conversation.addMessage(message);
            conversationRepository.save(conversation);

            return message;
        } else {
            throw new ResourceNotFoundException("Message hast to have Conversation assigned");
        }
    }

    @Override
    public Message deleteById(Long id) {

        Optional<Message> message = messageRepository.findById(id);

        return message.map(foundMessage -> {
            Conversation conversation = foundMessage.getConversation();

            if (conversation != null) {
                conversation.removeMessage(foundMessage);
                messageRepository.deleteById(id);
                conversationRepository.save(conversation);

                return foundMessage;
            } else {
                throw new ResourceNotFoundException("Message does not have Conversation assigned");
            }
        }).orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("Message", id)));
    }
}
