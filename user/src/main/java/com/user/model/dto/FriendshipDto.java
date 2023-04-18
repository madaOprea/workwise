package com.user.model.dto;

import com.user.model.Status;
import jakarta.persistence.Convert;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipDto {

    private String id;

    private RequestedUserDto user;

    private RequestedUserDto friend;

    @Convert(converter = StatusConverter.class)
    private Status status;
}