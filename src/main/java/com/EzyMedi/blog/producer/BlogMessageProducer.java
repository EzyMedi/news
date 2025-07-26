package com.EzyMedi.blog.producer;
import com.EzyMedi.blog.dto.BlogMessage;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlogMessageProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange fanoutExchange;  // thay vì lấy String

    public void sendBlogMessage(BlogMessage message) {
        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", message);
    }
}
