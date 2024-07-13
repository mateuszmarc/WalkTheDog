package com.mateuszmarcyk.walkthedog.walkevent.dto;

import com.mateuszmarcyk.walkthedog.user.dto.UserDTO;
import com.mateuszmarcyk.walkthedog.walkevent.WalkStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class WalkEventDTO {

    private Long id;
    private UserDTO creator;
    private List<UserDTO> participants;
    private LocalDateTime createdAt;
    private LocalDateTime startTime;
    private WalkStatus status;
}
