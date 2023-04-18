package com.comment.service;

import com.comment.repository.CommentRepository;
import com.comment.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImplementation implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(String id, Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }

    @Override
    public void deleteBlogById(String id) {
        commentRepository.deleteById(id);
    }

}
