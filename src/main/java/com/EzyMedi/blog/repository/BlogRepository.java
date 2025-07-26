package com.EzyMedi.blog.repository;

import com.EzyMedi.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BlogRepository extends JpaRepository<Blog, UUID> {
}
