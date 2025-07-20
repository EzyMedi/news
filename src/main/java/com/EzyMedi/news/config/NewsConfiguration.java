package com.EzyMedi.news.config;

import lombok.Getter;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class NewsConfiguration {
    @Value("${spring.rabbitmq.exchange.news}")
    private String newsExchange;

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(newsExchange);
    }

}
