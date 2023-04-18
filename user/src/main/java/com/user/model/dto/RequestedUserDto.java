package com.user.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestedUserDto {

    @NotNull
    private String name;

//    @EmailValidator
    @NotNull
    private String email;

//    @PasswordValidator
    @NotNull
    private String password;

    private boolean active;
}
