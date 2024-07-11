package com.mateuszmarcyk.walkthedog.message;

import com.mateuszmarcyk.walkthedog.conversation.Conversation;
import com.mateuszmarcyk.walkthedog.exception.ResourceNotFoundException;
import com.mateuszmarcyk.walkthedog.messagenotification.MessageNotification;
import com.mateuszmarcyk.walkthedog.messagenotification.MessageNotificationService;
import com.mateuszmarcyk.walkthedog.notification.NotificationStatus;
import com.mateuszmarcyk.walkthedog.user.User;
import com.mateuszmarcyk.walkthedog.user.UserService;
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


    private final UserService userService;
    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private String resourceNotFoundExceptionMessage;

    private final MessageRepository messageRepository;
    private final MessageNotificationService messageNotificationService;

    @Override
    public Message save(Message message) {

        User sender = message.getSender();
        User receiver = message.getReceiver();

        Conversation conversation = message.getConversation();

        if (conversation == null) {

            conversation = new Conversation();
            sender.addConversation(conversation);
            receiver.addConversation(conversation);

        }

        MessageNotification messageNotification = new MessageNotification(
                message.getReceiver(),
                NotificationStatus.UNREAD,
                message
        );


        message.setMessageNotification(messageNotification);
        conversation.addMessage(message);


        userService.save(sender);
        userService.save(receiver);

        return message;
    }

    @Override
    public Message deleteById(Long id) {

        Optional<Message> message = messageRepository.findById(id);

        return message.map(foundMessage -> {
            Conversation conversation = foundMessage.getConversation();

            User sender = foundMessage.getSender();
            User receiver = foundMessage.getReceiver();


            if (conversation != null) {
                conversation.removeMessage(foundMessage);

                receiver.removeReceivedMessage(foundMessage);
                sender.removeSentMessage(foundMessage);

                if (foundMessage.getMessageNotification() != null) {
                    messageNotificationService.delete(foundMessage.getMessageNotification());
                }
                messageRepository.deleteById(id);

                userService.save(sender);
                userService.save(receiver);

                return foundMessage;
            } else {
                throw new ResourceNotFoundException("Message does not have Conversation assigned");
            }
        }).orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("Message", id)));
    }
}
