package com.mapleleaf.profileservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${mapleleaf.authQueue}")
    String queueName;
    @Value("${mapleleaf.authResponseQueue}")
    String queueResponseName;
    @Value("${mapleleaf.exchange}")
    String exchange;
    @Value("${mapleleaf.routingkey}")
    String routingKey;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    Queue queueResponse() {
        return new Queue(queueResponseName, false);
    }


    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding(DirectExchange exchange) {
        return BindingBuilder.bind(queue()).to(exchange).with(routingKey);
    }

    @Bean
    Binding bindingResponse(DirectExchange exchange) {
        return BindingBuilder.bind(queueResponse()).to(exchange).with(routingKey);
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
