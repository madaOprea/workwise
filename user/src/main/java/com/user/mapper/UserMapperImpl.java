package com.user.mapper;

import com.user.model.User;
import com.user.model.dto.RequestedUserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public List<RequestedUserDto> toUserDtos(List<User> usersList) {
        if ( usersList == null ) {
            return null;
        }

        List<RequestedUserDto> list = new ArrayList<RequestedUserDto>( usersList.size() );
        for ( User user : usersList ) {
            list.add( toRequestedUserDto( user ) );
        }

        return list;
    }

    @Override
    public RequestedUserDto toRequestedUserDto(User user) {
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

    @Override
    public User toUser(RequestedUserDto requestedUserDto) {
        if ( requestedUserDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.encodedPassword(UserMapper.encodePassword(requestedUserDto.getPassword()));
        user.name( requestedUserDto.getName() );
        user.email( requestedUserDto.getEmail() );
        user.active( requestedUserDto.isActive() );

        return user.build();
    }
}
