package com.EzyMedi.news.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class NewsMessage implements Serializable {
    private UUID newsId;
    private String title;
    private UUID doctorId;
}