package com.blog.controller;

import com.blog.model.Blog;
import com.blog.service.BlogService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogsByUser(String userId) {
        log.debug("***"  + this.getClass() + " getAllByAdmin /n ");

        List<Blog> group = blogService.getAllByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(group);
    }

    @PostMapping
    public ResponseEntity<Blog> saveBlog(@RequestBody @Valid Blog blog) {
        log.info("***"  + this.getClass() + " saveBlog /n ");

        blogService.save(blog);
        return ResponseEntity.status(HttpStatus.OK).body(blog);
    }

    @PutMapping("/{blogId}")
    public ResponseEntity<Blog> updateBlog(@PathVariable String blogId, @RequestBody Blog blog) {
        log.info("***" + this.getClass() + " --- updateBlog method ");

        Blog blogUpdated = new Blog();
        try {
            blogUpdated = blogService.update(blogId, blog);
        } catch(Exception e) {
            log.error(" --- The error is: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(blogUpdated);
        }
        return ResponseEntity.status(HttpStatus.OK).body(blogUpdated);
    }
}
