package com.blog.repository;

import com.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("blogsRepository")
public interface BlogRepository extends JpaRepository<Blog, String> {

    @Query(value = "select b from Blog b where b.user = :userId")
    List<Blog> findAllBlogsByUser(String userId);

    List<Blog> findAll();
}
