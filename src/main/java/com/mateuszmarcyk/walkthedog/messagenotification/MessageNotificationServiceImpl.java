package com.mateuszmarcyk.walkthedog.messagenotification;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MessageNotificationServiceImpl implements MessageNotificationService {

    private final MessageNotificationRepository messageNotificationRepository;

    @Override
    public MessageNotification save(MessageNotification messageNotification) {
        messageNotificationRepository.save(messageNotification);

        return messageNotification;
    }

    @Override
    public MessageNotification delete(MessageNotification messageNotification) {
        messageNotificationRepository.delete(messageNotification);
        return messageNotification;
    }

}
