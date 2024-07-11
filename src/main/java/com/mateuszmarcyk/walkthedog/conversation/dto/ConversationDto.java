package com.mateuszmarcyk.walkthedog.conversation.dto;

import com.mateuszmarcyk.walkthedog.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ConversationDto {

    private final Long id;
    private final User user1;
    private final User user2;

}
