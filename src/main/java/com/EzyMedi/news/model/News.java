package com.EzyMedi.news.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "news")
public class News {
    @GeneratedValue
    @Id
    private UUID postId;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private UUID authorId;
    @Column
    private Date publishedDate;
    @Column
    private Date updatedDate;
}
