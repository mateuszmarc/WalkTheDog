package com.mateuszmarcyk.walkthedog.walkeventinvitation;


public interface WalkEventInvitationService {

    WalkEventInvitation findById(Long id);

    WalkEventInvitation save(WalkEventInvitation walkEventInvitation);

    WalkEventInvitation deleteById(Long id);

    WalkEventInvitation delete(WalkEventInvitation walkinvitation);

}
