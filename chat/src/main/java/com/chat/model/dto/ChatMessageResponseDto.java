package com.chat.model.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor

public class ChatMessageResponseDto {

    private String chatId;
    private Long id;
}
