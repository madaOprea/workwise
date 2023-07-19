package com.comment.service;

import com.comment.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComments();
    Comment save(Comment comment);
    Comment update(String id, Comment comment);
    void deleteCommentById(String id);
}
