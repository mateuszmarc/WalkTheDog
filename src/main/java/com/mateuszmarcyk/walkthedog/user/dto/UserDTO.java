package com.mateuszmarcyk.walkthedog.user.dto;

import com.mateuszmarcyk.walkthedog.conversation.dto.ConversationDTO;
import com.mateuszmarcyk.walkthedog.dog.dto.DogDTO;
import com.mateuszmarcyk.walkthedog.friendrequest.dto.FriendRequestDTO;
import com.mateuszmarcyk.walkthedog.friendrequestnotification.dto.FriendRequestNotificationDTO;
import com.mateuszmarcyk.walkthedog.message.dto.MessageDTO;
import com.mateuszmarcyk.walkthedog.messagenotification.dto.MessageNotificationDTO;
import com.mateuszmarcyk.walkthedog.walkevent.dto.WalkEventDTO;
import com.mateuszmarcyk.walkthedog.walkeventinvitation.dto.WalkEventInvitationDTO;
import com.mateuszmarcyk.walkthedog.walkinvitationnotification.dto.WalkEventInvitationNotificationDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "Imię jest wymagane")
    @Size(min = 1, message = "Imię jest wymagane")
    private String firstName;

    @NotBlank(message = "Nazwisko jest wymagane")
    @Size(min = 1, message = "Nazwisko jest wymagane")
    private String lastName;

    @NotBlank(message = "Nazwa użytkownika jest wymagana")
    @Size(min = 3, max = 20, message = "Nazwa użytkownika musi mieć od 3 do 20 znaków")
    private String username;

    @NotBlank(message = "Email jest wymagany")
    @Email(message = "Email powinien być poprawny")
    private String email;

    @NotBlank(message = "Hasło jest wymagane")
    @Size(min = 6, message = "Hasło musi mieć co najmniej 6 znaków")
    private String password;

    private String phoneNumber;

    private String address;

    private String bio;

    private String profileImageUrl;

    @NotNull(message = "Rola użytkownika jest wymagana")
    private String appUserRole;

    private Boolean enabled = false;

    private List<DogDTO> dogsDto;

    private List<FriendRequestDTO> sentFriendRequestsDto;
    private List<FriendRequestDTO> receivedFriendRequestsDto;

    private List<MessageDTO> sentMessagesDto;
    private List<MessageDTO> receivedMessagesDto;

    private List<UserDTO> friendsDto;

    private List<ConversationDTO> conversationsDto;

    private List<WalkEventDTO> walkEventsDto;

    private List<FriendRequestNotificationDTO> friendRequestNotificationsDto;

    private List<WalkEventInvitationNotificationDTO> walkEventInvitationNotificationsDto;

    private List<MessageNotificationDTO> messageNotificationsDto;

    private List<WalkEventInvitationDTO> sentWalkEventInvitationsDto;
    private List<WalkEventInvitationDTO> receivedWalkEventInvitationsDto;

    private List<WalkEventDTO> createdWalkEventsDto;
}

