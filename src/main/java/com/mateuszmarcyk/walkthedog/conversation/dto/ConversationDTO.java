package com.mateuszmarcyk.walkthedog.conversation.dto;

import com.mateuszmarcyk.walkthedog.message.dto.MessageDTO;
import com.mateuszmarcyk.walkthedog.user.dto.UserDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ConversationDTO {

    private Long id;
    private List<UserDTO> usersDto;
    private LocalDateTime createdAt;
    private List<MessageDTO> messagesDto;
}