package com.EzyMedi.blog.controller;

import com.EzyMedi.blog.dto.BlogSendDto;
import com.EzyMedi.blog.model.Blog;
import com.EzyMedi.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/create")
    ResponseEntity<String> createBlog(@RequestBody BlogSendDto blogDto, @RequestParam UUID doctorId) {
        return blogService.createBlog(doctorId, blogDto);
    }

    //single Blog
    @GetMapping("/{id}/get")
    Blog getBlogById(@PathVariable UUID id) {
        return blogService.getBlogById(id);
    }

    @GetMapping("/get")
    List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @PutMapping("/{id}/update")
    Blog updateBlog(@PathVariable UUID id, @RequestBody Blog blog) {
        return blogService.updateBlog(id, blog);
    }

    @DeleteMapping("/{id}/delete")
    void deleteBlog(@PathVariable UUID id) {
        blogService.deleteBlog(id);
    }
}
