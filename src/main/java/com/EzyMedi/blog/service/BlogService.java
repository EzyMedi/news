package com.EzyMedi.blog.service;

import com.EzyMedi.blog.dto.BlogMessage;
import com.EzyMedi.blog.dto.BlogSendDto;
import com.EzyMedi.blog.model.Blog;
import com.EzyMedi.blog.producer.BlogMessageProducer;
import com.EzyMedi.blog.repository.BlogRepository;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private BlogMessageProducer blogMessageProducer;

    public void publish(BlogMessage message) {
        blogMessageProducer.sendBlogMessage(message);
        System.out.println("blog broadcasted to all");
    }

    public ResponseEntity<String> createBlog(UUID doctorId, BlogSendDto blogDto) {
        Blog blog = new Blog();
        blog.setDoctorId(doctorId);
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        blog.setPublishedDate(new Date());
        blogRepository.save(blog);

        BlogMessage message = new BlogMessage();
        message.setBlogId(blog.getPostId());
        message.setTitle(blog.getTitle());
        message.setDoctorId(blog.getDoctorId());
        publish(message);
     return ResponseEntity.ok("Blog post created ");
    }
    @Cacheable(value = "blogs", key = "#blogId")
    public Blog getBlogById(UUID blogId) {
        return blogRepository.findById(blogId).orElse(null);
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }
    @CachePut(value = "blogs", key = "#blogId")
    public Blog updateBlog(UUID blogId, Blog blog) {
        Blog post = blogRepository.findById(blogId).orElse(null);
        if (post != null) {
            post.setTitle(blog.getTitle());
            post.setContent(blog.getContent());
            post.setUpdatedDate(new Date());
            blogRepository.save(post);
        }
        return post;
    }
    @CacheEvict(value = "blogs", key = "#blogId")
    public void deleteBlog( UUID blogId) {
        blogRepository.deleteById(blogId);
    }
}
