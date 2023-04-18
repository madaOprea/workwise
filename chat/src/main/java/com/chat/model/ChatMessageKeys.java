package com.chat.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@Embeddable
@NoArgsConstructor
public class ChatMessageKeys implements Serializable {

    @NonNull
    private String chatId;

    @NonNull
    private Long id;
}

