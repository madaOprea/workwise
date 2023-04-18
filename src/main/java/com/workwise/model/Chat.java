package com.workwise.model;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
public class Chat {

    private String chatId;
    private Long id;
    private String sender;
    private String receiver;
    private String message;
    private LocalDateTime time;
    private String status;
}
