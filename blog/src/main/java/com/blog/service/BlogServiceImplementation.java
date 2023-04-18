package com.blog.service;

import com.blog.model.Blog;
import com.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BlogServiceImplementation implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> getAll() {
        return blogRepository.findAll();
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

    @Override
    public void deleteBlogById(String id) {
        blogRepository.deleteById(id);
    }
}
