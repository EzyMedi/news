package com.EzyMedi.news.controller;

import com.EzyMedi.news.dto.NewsSendDto;
import com.EzyMedi.news.model.News;
import com.EzyMedi.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/create")
    ResponseEntity<String> createNews(@RequestBody NewsSendDto newsDto, @RequestParam UUID doctorId) {
        return newsService.createNews(doctorId, newsDto);
    }

    //single news
    @GetMapping("/{id}/get")
    News getNewsById(@PathVariable UUID id) {
        return newsService.getNewsById(id);
    }

    @GetMapping("/get")
    List<News> getAllNews() {
        return newsService.getAllNews();
    }

    @PutMapping("/{id}/update")
    News updateNews(@PathVariable UUID id, @RequestBody News news) {
        return newsService.updateNews(id, news);
    }

    @DeleteMapping("/{id}/delete")
    void deleteNews(@PathVariable UUID id) {
        newsService.deleteNews(id);
    }
}
