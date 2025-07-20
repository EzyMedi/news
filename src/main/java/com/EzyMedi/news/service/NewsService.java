package com.EzyMedi.news.service;

import com.EzyMedi.news.dto.NewsMessage;
import com.EzyMedi.news.dto.NewsReceiveDto;
import com.EzyMedi.news.model.News;
import com.EzyMedi.news.producer.NewsMessageProducer;
import com.EzyMedi.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.EzyMedi.user.data.model.User;
import java.util.Date;
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

    public ResponseEntity<String> createNews(UUID doctorId, NewsReceiveDto newsDto) {
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

//        String fullUrl = urlGetAllFollowers + "/" + doctorId;
//        ResponseEntity<User[]> response = restTemplate.getForEntity(fullUrl, User[].class);
//        User[] followers = response.getBody();
//
//        // Step 3: Notify each follower (e.g., print to console, or eventually send real notifications)
//        if (followers != null) {
//            for (User follower : followers) {
//                // Example notification logic (replace with actual implementation, e.g., message queue, push notification)
//                System.out.println("Notified follower: " + follower.getFullName() + " about new post: " + news.getTitle());
//            }
//        }

//        return ResponseEntity.ok("News post created and notifications sent to " + (followers != null ? followers.length : 0) + " followers.");
        return ResponseEntity.ok("News post created ");
    }
}
