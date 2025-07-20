package com.EzyMedi.news.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class NewsMessage {
    private UUID newsId;
    private String title;
    private UUID doctorId;
}