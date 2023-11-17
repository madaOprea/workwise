package com.user.model;

import com.user.utils.annotations.EmailValidator;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String name;

    @Column
    private String encodedPassword;

    @Column
    @EmailValidator
    private String email;

    @Column
    private Boolean active;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime lastModifiedDate;
}