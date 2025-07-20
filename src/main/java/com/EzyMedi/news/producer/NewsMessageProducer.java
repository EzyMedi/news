package com.EzyMedi.news.producer;
import com.EzyMedi.news.config.NewsConfiguration;
import com.EzyMedi.news.dto.NewsMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsMessageProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private NewsConfiguration newsConfiguration;

    public void sendNewsMessage(NewsMessage message) {
        rabbitTemplate.convertAndSend(newsConfiguration.getNewsExchange(), "", message);
    }
}
