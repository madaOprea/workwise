package com.chat.model.mapper;

import com.chat.model.ChatMessage;
import com.chat.model.dto.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ChatMessageMapper {
    ChatMessageMapper chatMessageMapper =  Mappers.getMapper(ChatMessageMapper.class);

    ChatMessageRequestDto toChatMessageRequestDto(ChatMessage chatMessage);
    ChatMessage toChatMessageFromChatMessageRequestDto(ChatMessageRequestDto dto);

    ChatMessageResponseDto toChatMessageResponseDto(ChatMessage chatMessage);
    ChatMessage toChatMessageFromChatMessageResponseDto(ChatMessageResponseDto dto);
}
