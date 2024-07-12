package com.mateuszmarcyk.walkthedog.walkeventinvitation;

import com.mateuszmarcyk.walkthedog.exception.ResourceNotFoundException;
import com.mateuszmarcyk.walkthedog.user.User;
import com.mateuszmarcyk.walkthedog.user.UserService;
import com.mateuszmarcyk.walkthedog.walkinvitationnotification.WalkEventInvitationNotification;
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
public class WalkEventInvitationServiceImpl implements WalkEventInvitationService {

    @Value("${resourceNotFoundExceptionMessage}")
    private String resourceNotFoundExceptionMessage;

    private final WalkEventInvitationRepository walkEventInvitationRepository;
    private final UserService userService;

    @Override
    public WalkEventInvitation findById(Long id) {

        Optional<WalkEventInvitation> walkInvitation = walkEventInvitationRepository.findById(id);

        return walkInvitation
                .orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("Walk Invitation", id)));

    }

    @Override
    public WalkEventInvitation save(WalkEventInvitation walkEventInvitation) {

        User sender = walkEventInvitation.getSender();
        User receiver = walkEventInvitation.getReceiver();

        if (sender != null && receiver != null) {

            WalkEventInvitationNotification notification = new WalkEventInvitationNotification(receiver, walkEventInvitation);
            walkEventInvitation.setWalkEventInvitationNotification(notification);

            sender.addSentWalkInvitation(walkEventInvitation);
            walkEventInvitation.setSender(sender);

            receiver.addReceivedWalkInvitation(walkEventInvitation);
            walkEventInvitation.setReceiver(receiver);
            receiver.addWalkInvitationNotification(notification);

            userService.save(sender);
            userService.save(receiver);
            return walkEventInvitation;
        } else {
            throw new ResourceNotFoundException("Walk event needs to have sender and receiver assigned");
        }
    }

    @Override
    public WalkEventInvitation deleteById(Long id) {

        WalkEventInvitation walkEventInvitation = findById(id);

        User sender = walkEventInvitation.getSender();
        User receiver = walkEventInvitation.getReceiver();
        WalkEventInvitationNotification walkEventInvitationNotification = walkEventInvitation.getWalkEventInvitationNotification();

        sender.removeSentWalkInvitation(walkEventInvitation);

        receiver.removeReceivedWalkInvitation(walkEventInvitation);
        if (walkEventInvitationNotification != null) {
            receiver.removeWalkInvitationNotification(walkEventInvitationNotification);
        }

        userService.save(sender);
        userService.save(receiver);
        return walkEventInvitation;
    }

    @Override
    public WalkEventInvitation delete(WalkEventInvitation walkEventInvitation) {
        return null;
    }
}
