package com.comment.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "blogs")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name="comment_uuid")
    private String commentId;

    @Column(name="blogId")
    private String blog;

    @Column(name="text")
    private String text;
}
