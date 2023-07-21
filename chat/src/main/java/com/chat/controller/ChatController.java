package com.chat.controller;

import com.chat.model.ChatMessage;
import com.chat.model.dto.*;
import com.chat.service.ChatMessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatMessageService chatMessageService;

    @PostMapping
    public ResponseEntity<ChatMessageResponseDto> newChat(@RequestBody ChatMessageRequestDto chatDto) {
        log.debug("/n ***" + this.getClass() + " newChat method /n");

        ChatMessageResponseDto newChat = chatMessageService.saveNewChat(chatDto);
        return ResponseEntity.status(HttpStatus.OK).body(newChat);
    }

    @PostMapping("/{chatId}")
    public ResponseEntity<ChatMessageResponseDto> replyChat(@PathVariable String chatId, @RequestBody ChatMessageRequestDto chatDto) {
        log.debug("/n ***" + this.getClass() + " replyChat method /n");

        ChatMessageResponseDto chat = chatMessageService.replyToMessageOnChat(chatId, chatDto);
        return ResponseEntity.status(HttpStatus.OK).body(chat);
    }

    @GetMapping("/getSpecificChat")
    public ResponseEntity<ChatMessage> getChatByChatIdAndId(@RequestBody ChatMessageResponseDto chatDto) {
        log.debug("/n ***" + this.getClass() + " getChatByChatIdAndId method /n");

        ChatMessage chat = chatMessageService.getChatMessageById(chatDto.getChatId(), chatDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body(chat);
    }

}
