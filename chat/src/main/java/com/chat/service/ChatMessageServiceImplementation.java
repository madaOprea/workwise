package com.chat.service;

import com.chat.model.*;
import com.chat.model.dto.*;
import com.chat.repository.ChatMessageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.chat.model.mapper.ChatMessageMapper.chatMessageMapper;

@Log4j2
@Service
public class ChatMessageServiceImplementation implements ChatMessageService {

    @Autowired
    private ChatMessageRepository chatRepository;

    public ChatMessage getChatMessageById (String chatId, Long id) {
        ChatMessageKeys keys = new ChatMessageKeys(chatId, id);
        return chatRepository.findById(keys).orElse(null);
    }

    @Override
    public ChatMessageResponseDto saveNewChat(ChatMessageRequestDto chatMessageRequestDto) {
        log.debug("/n ***" + this.getClass() + " saveNewChat method ");

        if (chatMessageRequestDto == null) {
            log.error("***" + this.getClass() + " saveNewChat method ");
            throw new EntityNotFoundException("*** This dto does not exist");
        }

        ChatMessage newChat =  chatMessageMapper.toChatMessageFromChatMessageRequestDto(chatMessageRequestDto);
        newChat.setTimestamp(LocalDateTime.now());
        newChat.setStatus(Status.UNSEEN);
        chatRepository.save(newChat);

        ChatMessageResponseDto chatMessageResponseDto = chatMessageMapper.toChatMessageResponseDto(newChat);
        return chatMessageResponseDto;
    }

    @Override
    public ChatMessageResponseDto replyToMessageOnChat(String chatId, ChatMessageRequestDto chatMessageDto) {
        log.debug("/n ***" + this.getClass() + " replyToMessageOnChat method ");

        // update status for previous chat
        Optional<ChatMessage> possiblePreviousChat = chatRepository.findByChatId(chatId).stream().findFirst();
        if (!possiblePreviousChat.isPresent()) {
            log.error("***" + this.getClass() + " replyToMessageOnChat method ");
            throw new EntityNotFoundException("*** This id does not exist");
        }
        ChatMessage previousChat = possiblePreviousChat.get();
        previousChat.setStatus(Status.SEEN);

        // create new entry to db
        ChatMessage replyMessage = chatMessageMapper.toChatMessageFromChatMessageRequestDto(chatMessageDto);
        replyMessage.setChatId(previousChat.getChatId());
        replyMessage.setId(previousChat.getId() + 1);
        replyMessage.setSender(previousChat.getReceiver());
        replyMessage.setReceiver(previousChat.getSender());
        replyMessage.setTimestamp(LocalDateTime.now());//auto
        replyMessage.setStatus(Status.UNSEEN);
        chatRepository.save(replyMessage);

        ChatMessageResponseDto chatMessageResponseDto = chatMessageMapper.toChatMessageResponseDto(replyMessage);
        return chatMessageResponseDto;
    }

}
