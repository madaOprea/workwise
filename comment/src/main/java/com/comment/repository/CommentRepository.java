package com.comment.repository;

import com.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("commentsRepository")
public interface CommentRepository extends JpaRepository<Comment, String> {
}
