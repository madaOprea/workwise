package com.workwise.dto;

import lombok.*;

@Builder
@Data
public class ChatDto {

    private String sender;
    private String receiver;
    private String message;
}
