package com.user.mapper;

import com.user.model.Friendship;
import com.user.model.User;
import com.user.model.dto.FriendshipDto;
import com.user.model.dto.RequestedUserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FriendshipMapperImpl implements FriendshipMapper {

    @Override
    public FriendshipDto toFriendshipDto(Friendship friendship) {
        if ( friendship == null ) {
            return null;
        }

        FriendshipDto.FriendshipDtoBuilder friendshipDto = FriendshipDto.builder();

        friendshipDto.id( friendship.getId() );
        friendshipDto.user( userToRequestedUserDto( friendship.getUser() ) );
        friendshipDto.friend( userToRequestedUserDto( friendship.getFriend() ) );
        friendshipDto.status( friendship.getStatus() );

        return friendshipDto.build();
    }

    @Override
    public Friendship toFriendship(FriendshipDto friendshipDto) {
        if ( friendshipDto == null ) {
            return null;
        }

        Friendship.FriendshipBuilder friendship = Friendship.builder();

        friendship.id( friendshipDto.getId() );
        friendship.user( requestedUserDtoToUser( friendshipDto.getUser() ) );
        friendship.friend( requestedUserDtoToUser( friendshipDto.getFriend() ) );
        friendship.status( friendshipDto.getStatus() );

        return friendship.build();
    }

    @Override
    public List<FriendshipDto> toFriendshipDtos(List<Friendship> friendshipList) {
        if ( friendshipList == null ) {
            return null;
        }

        List<FriendshipDto> list = new ArrayList<FriendshipDto>( friendshipList.size() );
        for ( Friendship friendship : friendshipList ) {
            list.add( toFriendshipDto( friendship ) );
        }

        return list;
    }

    protected RequestedUserDto userToRequestedUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        RequestedUserDto.RequestedUserDtoBuilder requestedUserDto = RequestedUserDto.builder();

        requestedUserDto.name( user.getName() );
        requestedUserDto.email( user.getEmail() );
        if ( user.getActive() != null ) {
            requestedUserDto.active( user.getActive() );
        }

        return requestedUserDto.build();
    }

    protected User requestedUserDtoToUser(RequestedUserDto requestedUserDto) {
        if ( requestedUserDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( requestedUserDto.getName() );
        user.email( requestedUserDto.getEmail() );
        user.active( requestedUserDto.isActive() );

        return user.build();
    }
}
