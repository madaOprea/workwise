package com.workwise.model;

import lombok.*;

@AllArgsConstructor
@Builder
@Data
public class UserResponse {

    private String id;
    private String name;
    private String email;
}
