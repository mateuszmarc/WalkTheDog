package com.mateuszmarcyk.walkthedog.friendrequest;

import com.mateuszmarcyk.walkthedog.exception.ResourceNotFoundException;
import com.mateuszmarcyk.walkthedog.friendrequest.enums.RequestStatus;
import com.mateuszmarcyk.walkthedog.user.User;
import com.mateuszmarcyk.walkthedog.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class FriendRequestServiceImpl implements FriendRequestService {


    @Value("${resourceNotFoundExceptionMessage}")
    private String resourceNotFoundExceptionMessage;

    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;

    @Override
    public FriendRequest save(FriendRequest friendRequest) {

        User sender = friendRequest.getSender();
        User receiver = friendRequest.getReceiver();

        if (sender != null && receiver != null) {

            friendRequest.setRequestStatus(RequestStatus.PENDING);

            sender.addSentFriendRequest(friendRequest);
            receiver.addReceivedFriendRequest(friendRequest);

            friendRequestRepository.save(friendRequest);

            userRepository.save(sender);
            userRepository.save(receiver);

            return friendRequest;
        } else {
            throw new ResourceNotFoundException("Cannot save Friend Request Without Sender and Receiver");
        }


    }

    public FriendRequest deleteById(Long id) {

       Optional<FriendRequest> friendRequest = friendRequestRepository.findById(id);

       return friendRequest.map(foundRequest -> {

           User sender = foundRequest.getSender();
           User receiver = foundRequest.getReceiver();

           if (sender != null && receiver != null) {

               foundRequest.setRequestStatus(RequestStatus.TO_DELETE);

               sender.removeSentFriendRequest(foundRequest);
               receiver.removeReceivedFriendRequest(foundRequest);
               userRepository.save(sender);
               userRepository.save(receiver);

               friendRequestRepository.deleteById(id);

               return foundRequest;
           } else {
               throw new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("Cannot save Friend Request Without Sender and Receiver"));
           }

       }).orElseThrow(() -> new ResourceNotFoundException(resourceNotFoundExceptionMessage.formatted("Friend request", id)));
    }
}
