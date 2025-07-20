package com.EzyMedi.news.controller;

import com.EzyMedi.news.model.News;
import com.EzyMedi.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class NewsController {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/news")
    News createNews(@RequestBody News news) {
        news.setPublishedDate(new Date());
        String message = "News created " + news.getTitle();
        restTemplate.postForEntity("http://localhost:8080/patient/notify-all", message, String.class);
        return newsRepository.save(news);
    }

    //single news
    @GetMapping("/news/{id}")
    News getNewsById(@PathVariable UUID id) {
        return newsRepository.findById(id).orElse(null);
    }

    @GetMapping("/news")
    List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @PutMapping("news/{id}")
    News updateNews(@PathVariable UUID id, @RequestBody News news) {
        News post = newsRepository.findById(id).orElse(null);
       if (post != null) {
           post.setTitle(news.getTitle());
           post.setContent(news.getContent());
           post.setUpdatedDate(new Date());
           newsRepository.save(post);
       }
       return post;
    }

    @DeleteMapping("news/{id}")
    void deleteNews(@PathVariable UUID id) {
        newsRepository.deleteById(id);
    }
}
