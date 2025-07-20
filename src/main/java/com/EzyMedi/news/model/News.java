package com.EzyMedi.news.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class News {
    @GeneratedValue
    @Id
    private UUID postId;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private UUID doctorId;
    @Column
    private Date publishedDate;
    @Column
    private Date updatedDate;
}
