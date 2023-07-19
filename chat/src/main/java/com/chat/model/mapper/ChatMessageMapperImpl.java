package com.chat.model.mapper;

import com.chat.model.ChatMessage;
import com.chat.model.dto.*;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageMapperImpl implements ChatMessageMapper {

    @Override
    public ChatMessageRequestDto toChatMessageRequestDto(ChatMessage chatMessage) {
        if ( chatMessage == null ) {
            return null;
        }

        ChatMessageRequestDto.ChatMessageRequestDtoBuilder chatMessageRequestDto = ChatMessageRequestDto.builder();

        chatMessageRequestDto.sender( chatMessage.getSender() );
        chatMessageRequestDto.receiver( chatMessage.getReceiver() );
        chatMessageRequestDto.message( chatMessage.getMessage() );

        return chatMessageRequestDto.build();
    }

    @Override
    public ChatMessage toChatMessageFromChatMessageRequestDto(ChatMessageRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        ChatMessage.ChatMessageBuilder chatMessage = ChatMessage.builder();

        chatMessage.sender( dto.getSender() );
        chatMessage.receiver( dto.getReceiver() );
        chatMessage.message( dto.getMessage() );

        return chatMessage.build();
    }

    @Override
    public ChatMessageResponseDto toChatMessageResponseDto(ChatMessage chatMessage) {
        if ( chatMessage == null ) {
            return null;
        }

        ChatMessageResponseDto.ChatMessageResponseDtoBuilder chatMessageResponseDto = ChatMessageResponseDto.builder();

        chatMessageResponseDto.chatId( chatMessage.getChatId() );
        chatMessageResponseDto.id( chatMessage.getId() );

        return chatMessageResponseDto.build();
    }

    @Override
    public ChatMessage toChatMessageFromChatMessageResponseDto(ChatMessageResponseDto dto) {
        if ( dto == null ) {
            return null;
        }

        ChatMessage.ChatMessageBuilder chatMessage = ChatMessage.builder();

        chatMessage.chatId( dto.getChatId() );
        chatMessage.id( dto.getId() );

        return chatMessage.build();
    }
}
