package com.stackroute.planpaymentservice.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
this class for configure rabbitMQ as a message broker
 */
@Configuration
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host ;

    @Value("${spring.rabbitmq.username}")
    private String username ;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.queue}")
    public String queue;

    @Value("${spring.rabbitmq.exchange}")
    public String exchange ;

    @Value("${spring.rabbitmq.routingKey}")
    public String routingKey ;

    // create queue for rabbitMQ
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    //create exchange for rabbitMQ
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    //create binding between Queue and TopicExchange for rabbitMQ
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    //Create stable connection between rabbitMq and application
    @Bean
    ConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    //create a message converter
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // create rabbitTemplate with a connection and message converter
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
