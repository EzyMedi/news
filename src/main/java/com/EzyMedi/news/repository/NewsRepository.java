package com.EzyMedi.news.repository;

import com.EzyMedi.news.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface NewsRepository extends JpaRepository<News, UUID> {
}
