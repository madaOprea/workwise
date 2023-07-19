package com.blog.service;

import com.blog.model.Blog;
import com.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImplementation implements BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogServiceImplementation(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<Blog> getAllByUser(String userId) {
        return blogRepository.findAllBlogsByUser(userId);
    }

    @Override
    public Blog save(Blog group) {
        return blogRepository.save(group);
    }

    @Override
    public Blog update(String id, Blog group) {
        return blogRepository.saveAndFlush(group);
    }
}
