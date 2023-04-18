package com.chat.model.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ChatMessageRequestDto {

    private String sender;
    private String receiver;
    private String message;
}
