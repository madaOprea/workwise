package com.comment.service;

import com.comment.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAll();
    Comment save(Comment comment);
    Comment update(String id, Comment comment);
    void deleteBlogById(String id);
}
