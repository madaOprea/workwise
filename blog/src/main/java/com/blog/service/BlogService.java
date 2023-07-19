package com.blog.service;

import com.blog.model.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> getAllByUser(String userId);
    Blog save(Blog group);
    Blog update(String id, Blog group);
}
