package com.mateuszmarcyk.walkthedog.message.dto;

import com.mateuszmarcyk.walkthedog.conversation.dto.ConversationDTO;
import com.mateuszmarcyk.walkthedog.messagenotification.dto.MessageNotificationDTO;
import com.mateuszmarcyk.walkthedog.user.dto.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MessageDTO {

    private Long id;

    @NotNull(message = "Conversation is required")
    private ConversationDTO conversation;

    @NotNull(message = "Sender is required")
    private UserDTO sender;

    @NotNull(message = "Receiver is required")
    private UserDTO receiver;

    @NotBlank(message = "Content is required")
    private String content;

    @NotNull(message = "Sent date is required")
    private LocalDateTime sentAt;

    private MessageNotificationDTO messageNotification;
}

