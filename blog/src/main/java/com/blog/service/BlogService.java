package com.blog.service;

import com.blog.model.Blog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {

    List<Blog> getAll();
    List<Blog> getAllByUser(String userId);
    Blog save(Blog group);
    Blog update(String id, Blog group);
    void deleteBlogById(String id);
}
