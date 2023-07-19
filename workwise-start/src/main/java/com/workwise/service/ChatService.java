package com.workwise.service;

import com.workwise.dto.ChatDto;
import com.workwise.model.Chat;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class ChatService {

    private final WebClient chatWebClientBuilder;

    public ChatService(WebClient.Builder webClient) {
        log.debug("*** creating webClient for Workwise." + this.getClass());

        this.chatWebClientBuilder = webClient.baseUrl("http://localhost:9003/chat").build();
    }

    public Mono<Chat> addChat(ChatDto chatDto) {
        log.debug("*** adding new chat from Workwise." + this.getClass());

        return this.chatWebClientBuilder.post()
                .bodyValue(chatDto)
                .retrieve().bodyToMono(Chat.class);
    }

    public Mono<Chat> replyToChat(String previousChatId, ChatDto chatDto) {
        log.debug("*** adding new reply from Workwise." + this.getClass());

        return this.chatWebClientBuilder.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/{previousChatId}")
                        .build(previousChatId))
                .bodyValue(chatDto)
                .retrieve().bodyToMono(Chat.class);
    }
}
