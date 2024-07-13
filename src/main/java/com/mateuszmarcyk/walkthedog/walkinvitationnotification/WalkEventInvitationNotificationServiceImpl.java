package com.mateuszmarcyk.walkthedog.walkinvitationnotification;

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
public class WalkEventInvitationNotificationServiceImpl implements WalkEventInvitationNotificationService {

    @Value("${resourceNotFoundExceptionMessage}")
    private String resourceNotFoundExceptionMessage;

    private final WalkEventInvitationNotificationRepository walkEventInvitationNotificationRepository;

    @Override
    public WalkEventInvitationNotification findById(Long id) {

        Optional<WalkEventInvitationNotification> notification = walkEventInvitationNotificationRepository.findById(id);

        return notification
                .orElseThrow(() -> new RuntimeException(resourceNotFoundExceptionMessage.formatted("Walk Event Invitation Notification", id)));
    }

    @Override
    public WalkEventInvitationNotification save(WalkEventInvitationNotification notification) {
       return walkEventInvitationNotificationRepository.save(notification);
    }

    @Override
    public WalkEventInvitationNotification deleteById(Long id) {
        return null;
    }

    @Override
    public WalkEventInvitationNotification delete(WalkEventInvitationNotification notification) {
        return null;
    }
}
