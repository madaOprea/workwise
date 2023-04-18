package com.user.mapper;

import com.user.model.Friendship;
import com.user.model.dto.FriendshipDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FriendshipMapper {

    FriendshipDto toFriendshipDto(Friendship friendship);
    Friendship toFriendship(FriendshipDto friendshipDto);
    List<FriendshipDto> toFriendshipDtos(List<Friendship> friendshipList);
}
