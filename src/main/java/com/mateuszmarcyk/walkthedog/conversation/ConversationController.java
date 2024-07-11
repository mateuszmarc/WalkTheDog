package com.mateuszmarcyk.walkthedog.conversation;

import com.mateuszmarcyk.walkthedog.conversation.dto.ConversationDto;
import com.mateuszmarcyk.walkthedog.user.User;
import com.mateuszmarcyk.walkthedog.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("users/conversations")
public class ConversationController {

    private final UserService userService;
    private final ConversationService conversationService;

    @GetMapping
    private String showConversations(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        String email = userDetails.getUsername();

        User user = userService.findByEmailFetchConversations(email);

        List<ConversationDto> conversationDTOs = conversationService.getConversationDtos(user);

        model.addAttribute("conversations", conversationDTOs);
        model.addAttribute("user", user);

        return "user-conversation-list";
    }

}