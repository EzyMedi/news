package com.EzyMedi.news.service;

import com.EzyMedi.news.dto.NewsReceiveDto;
import com.EzyMedi.news.model.News;
import com.EzyMedi.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.EzyMedi.user.data.dto.DoctorDTO;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${api.notify.all.base-url}")
    String urlNotifyAll;
    @Value("${api.get.doctor.base-url}")
    String urlGetDoctor;
    public ResponseEntity<String> createNews(UUID doctorId, NewsReceiveDto newsDto) {
        DoctorDTO doctorCheck;
        try {
            doctorCheck = restTemplate.getForObject(urlGetDoctor + doctorId, DoctorDTO.class);
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to fetch customer with id: " + doctorId, e);
        }
        // Step 2: Create and save news post
        News news = new News();
        news.setAuthorId(doctorId);
        news.setTitle(newsDto.getTitle());
        news.setContent(newsDto.getContent());
        news.setPublishedDate(new Date());
        newsRepository.save(news);



        return ResponseEntity.ok("News post created and notifications sent.");
    }

}
