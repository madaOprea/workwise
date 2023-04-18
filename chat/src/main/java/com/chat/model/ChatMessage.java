package com.chat.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
@Entity
@IdClass(ChatMessageKeys.class)
@NoArgsConstructor
@Table(name = "chat")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID, generator = "chatId_uuid")
    @GenericGenerator(name="chatId_uuid", strategy = "uuid")
    @Column(name="chatId_uuid")
    private String chatId;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name = "user1_uuid")
    private String sender;

    @Column(name = "user2_uuid")
    private String receiver;

    @Column
    private String message;

    @Column
    private LocalDateTime timestamp;

    @Column @Getter @Setter
    @Enumerated(EnumType.STRING)
    private Status status;
}
