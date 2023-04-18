package com.chat.services;

import com.chat.model.ChatMessage;
import com.chat.model.dto.*;

public interface ChatMessageService {

    ChatMessage getChatMessageById(String chatId, Long id);
    ChatMessageResponseDto saveNewChat(ChatMessageRequestDto chatMessageRequestDto);
    ChatMessageResponseDto replyToMessageOnChat(String chatId, ChatMessageRequestDto chatMessageRequestDto);
}
