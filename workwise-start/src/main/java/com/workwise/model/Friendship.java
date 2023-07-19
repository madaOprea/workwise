package com.workwise.model;

import com.workwise.dto.UserDto;
import lombok.*;

@Builder
@Data
public class Friendship {

    private String id;
    private UserDto user;
    private UserDto friend;
    private String status;
}
