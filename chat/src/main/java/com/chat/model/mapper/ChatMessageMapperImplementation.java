package com.chat.model.mapper;

import com.chat.model.ChatMessage;
import com.chat.model.dto.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ChatMessageMapperImplementation implements ChatMessageMapper{

    @Override
    public ChatMessageRequestDto toChatMessageRequestDto(ChatMessage chatMessage) {
        log.debug("/n ***" + this.getClass() + " toChatMessageRequestDto method ");

        if (chatMessage == null) {
            log.error("***" + this.getClass() + " returns null");
            return null;
        }

        ChatMessageRequestDto chatMessageRequestDto = new ChatMessageRequestDto();
        chatMessageRequestDto.setSender(chatMessage.getSender());
        chatMessageRequestDto.setReceiver(chatMessage.getReceiver());
        chatMessageRequestDto.setMessage(chatMessage.getMessage());

        return chatMessageRequestDto;
    }

    @Override
    public ChatMessage toChatMessageFromChatMessageRequestDto(ChatMessageRequestDto chatMessageRequestDto) {
        log.debug("/n ***" + this.getClass() + " toChatMessageFromChatMessageRequestDto method ");

        if (chatMessageRequestDto == null) {
            log.error("***" + this.getClass() + " toChatMessageFromChatMessageRequestDto method returns null");
            return null;
        }

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(chatMessageRequestDto.getSender());
        chatMessage.setReceiver(chatMessageRequestDto.getReceiver());
        chatMessage.setMessage(chatMessageRequestDto.getMessage());

        return chatMessage;
    }

    @Override
    public ChatMessageResponseDto toChatMessageResponseDto(ChatMessage chatMessage) {
        log.debug("/n ***" + this.getClass() + " toChatMessageResponseDto method ");

        if (chatMessage == null) {
            log.error("***" + this.getClass() + " toChatMessageResponseDto method returns null");
            return null;
        }

        ChatMessageResponseDto chatMessageResponseDto = new ChatMessageResponseDto();
        chatMessageResponseDto.setChatId(chatMessage.getChatId());
        chatMessageResponseDto.setId(chatMessage.getId());

        return chatMessageResponseDto;
    }

    @Override
    public ChatMessage toChatMessageFromChatMessageResponseDto(ChatMessageResponseDto chatMessageResponseDto) {
        log.debug("/n ***" + this.getClass() + " toChatMessageFromChatMessageResponseDto method ");

        if (chatMessageResponseDto == null) {
            log.error("***" + this.getClass() + " toChatMessageResponseDto method returns null");
            return null;
        }

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setChatId(chatMessageResponseDto.getChatId());
        chatMessage.setId(chatMessageResponseDto.getId());
        return chatMessage;
    }
}
