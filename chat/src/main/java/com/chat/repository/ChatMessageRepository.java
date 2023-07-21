package com.chat.repository;

import com.chat.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("chatRepository")
public interface ChatMessageRepository extends JpaRepository<ChatMessage, ChatMessageKeys> {

    @Query("select c from ChatMessage c where c.chatId = ?1 order by c.timestamp asc")
    List<ChatMessage> findByChatId(String chatId);

}