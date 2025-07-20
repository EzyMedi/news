package com.EzyMedi.news.service;

import com.EzyMedi.news.dto.NewsMessage;
import com.EzyMedi.news.dto.NewsSendDto;
import com.EzyMedi.news.model.News;
import com.EzyMedi.news.producer.NewsMessageProducer;
import com.EzyMedi.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private RestTemplate restTemplate;
//    @Value("${api.get.all.followers.url}")
//    String urlGetAllFollowers;
    @Autowired
    private NewsMessageProducer newsMessageProducer;

    public void publish(NewsMessage message) {
        newsMessageProducer.sendNewsMessage(message);
        System.out.println("News broadcasted to all");
    }

    public ResponseEntity<String> createNews(UUID doctorId, NewsSendDto newsDto) {
        News news = new News();
        news.setDoctorId(doctorId);
        news.setTitle(newsDto.getTitle());
        news.setContent(newsDto.getContent());
        news.setPublishedDate(new Date());
        newsRepository.save(news);

        NewsMessage message = new NewsMessage();
        message.setNewsId(news.getPostId());
        message.setTitle(news.getTitle());
        message.setDoctorId(news.getDoctorId());
        publish(message);
     return ResponseEntity.ok("News post created ");
    }
    //single news
    public News getNewsById(UUID id) {
        return newsRepository.findById(id).orElse(null);
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public News updateNews( UUID id, News news) {
        News post = newsRepository.findById(id).orElse(null);
        if (post != null) {
            post.setTitle(news.getTitle());
            post.setContent(news.getContent());
            post.setUpdatedDate(new Date());
            newsRepository.save(post);
        }
        return post;
    }

    public void deleteNews( UUID id) {
        newsRepository.deleteById(id);
    }
}
