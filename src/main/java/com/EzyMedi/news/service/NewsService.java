package com.EzyMedi.news.service;

import com.EzyMedi.news.model.News;
import com.EzyMedi.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;
//    public News createNews(News news) {
//        String urlDoctor = "http://localhost:8080/doctor/";
//        DoctorDTO doctor;
//
//    }
}
