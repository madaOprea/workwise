package com.blog.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name="blog_uuid")
    private String blogId;

    @Column(name="userId")
    private String user;

    @Column(name="description")
    private String description;

    @Column(name="priority")
    private String priority;

    private List<String> commentsList;
}
