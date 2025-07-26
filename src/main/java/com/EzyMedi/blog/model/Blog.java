package com.EzyMedi.blog.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Blog implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
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
