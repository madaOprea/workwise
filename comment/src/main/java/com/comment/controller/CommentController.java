package com.comment.controller;

import com.comment.model.Comment;
import com.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        log.debug("***"  + this.getClass() + " getAllCommentsByBlogId /n ");

        List<Comment> comments = commentService.getAllComments();
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody @Valid Comment comment) {
        log.info("***"  + this.getClass() + " saveComment /n ");

        commentService.save(comment);
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

    @PutMapping("/{blogId}")
    public ResponseEntity<Comment> updateComment(@PathVariable String commentId, @RequestBody Comment comment) {
        log.info("***" + this.getClass() + " --- updateComment method ");

        Comment commentUpdated = new Comment();
        try {
            commentUpdated = commentService.update(commentId, comment);
        } catch(Exception e) {
            log.error(" --- The error is: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(commentUpdated);
        }
        return ResponseEntity.status(HttpStatus.OK).body(commentUpdated);
    }
}
