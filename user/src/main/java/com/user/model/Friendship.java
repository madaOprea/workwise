package com.user.model;

import com.user.model.dto.StatusConverter;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "friendship")
public class Friendship {

    @Id
    @Column(name = "friendship_uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "user_uuid")
    private User user;

    @OneToOne
    @JoinColumn (name = "friend_uuid")
    private User friend;

    @Column
    @Convert(converter = StatusConverter.class)
    @Enumerated(EnumType.STRING)
    private Status status;
}
