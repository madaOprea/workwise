package com.chat.model;

import lombok.*;

@ToString
public enum Status {
    SEEN("seen"),
    UNSEEN("unseen"),
    MARK_AS_READ("mark as read"),
    MARK_AS_UNREAD("mark as unread");

    @Getter @Setter private String statusMessage;

    Status(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
