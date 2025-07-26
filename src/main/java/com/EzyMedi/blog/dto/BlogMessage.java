package com.EzyMedi.blog.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class BlogMessage implements Serializable {
    private UUID blogId;
    private String title;
    private UUID doctorId;
}