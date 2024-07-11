package com.mateuszmarcyk.walkthedog.friendrequest;


public interface FriendRequestService {

    FriendRequest save(FriendRequest friendRequest);

    FriendRequest deleteById(Long id);

}
