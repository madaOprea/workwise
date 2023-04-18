package com.user.mapper;

import com.user.model.User;
import com.user.model.dto.RequestedUserDto;
import org.mapstruct.*;

import java.util.*;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    List<RequestedUserDto> toUserDtos(List<User> usersList);
    RequestedUserDto toRequestedUserDto(User user);

    @Mapping(source = "password", target = "encodedPassword", qualifiedByName = "encodePassword")
    User toUser(RequestedUserDto requestedUserDto);

    @Named("encodePassword")
    static String encodePassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
}
