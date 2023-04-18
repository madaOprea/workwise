package com.user.model;

import lombok.*;

@ToString
public enum Status {
    PENDING("pending"),
    ACCEPTED("accepted"),
    ACTIVE("active"),
    REJECTED("rejected"),
    BLOCKED("blocked");

    @Getter @Setter private String statusFriendship;

    Status(String statusFriendship) {
        this.statusFriendship = statusFriendship;
    }
}
