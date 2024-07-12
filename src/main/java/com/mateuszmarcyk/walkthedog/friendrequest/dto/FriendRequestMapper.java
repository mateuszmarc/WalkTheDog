package com.mateuszmarcyk.walkthedog.friendrequest.dto;

import com.mateuszmarcyk.walkthedog.friendrequest.FriendRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FriendRequestMapper {
    FriendRequestMapper INSTANCE = Mappers.getMapper(FriendRequestMapper.class);

    FriendRequest toEntity(FriendRequestDTO friendRequestDTO);

    FriendRequestDTO toDTO(FriendRequest friendRequest);
}
