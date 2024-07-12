package com.mateuszmarcyk.walkthedog.walkinvitation;


public interface WalkInvitationService {

    WalkInvitation findById(Long id);

    WalkInvitation save(WalkInvitation walkInvitation);

    WalkInvitation deleteById(Long id);

    WalkInvitation delete(WalkInvitation walkinvitation);

}
