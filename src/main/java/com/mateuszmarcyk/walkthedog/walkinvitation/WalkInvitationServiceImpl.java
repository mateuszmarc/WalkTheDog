package com.mateuszmarcyk.walkthedog.walkinvitation;

import com.mateuszmarcyk.walkthedog.exception.ResourceNotFoundException;
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
public class WalkInvitationServiceImpl implements WalkInvitationService {

    @Value("${resourceNotFoundExceptionMessage}")
    private String resourceNotFoundExceptionMessage;

    private final WalkInvitationRepository walkInvitationRepository;

    @Override
    public WalkInvitation findById(Long id) {

        Optional<WalkInvitation> walkInvitation = walkInvitationRepository.findById(id);

        return walkInvitation
                .orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("Walk Invitation", id)));

    }

    @Override
    public WalkInvitation save(WalkInvitation walkInvitation) {
        return walkInvitationRepository.save(walkInvitation);
    }

    @Override
    public WalkInvitation deleteById(Long id) {
        return null;

    }

    @Override
    public WalkInvitation delete(WalkInvitation walkinvitation) {
        return null;
    }
}
