package com.stackroute.graphcommandservice.Config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqScoreConsumerConfig {

    @Value("${spring.rabbitmq.queue}")
    private String queue1;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange1;

    @Value("${spring.rabbitmq.routing_key}")
    private String routing_key1;

    @Value("${spring.rabbitmq.queue2}")
    private String queue2;

    @Value("${spring.rabbitmq.exchange2}")
    private String exchange2;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.routing_key2}")
    private String routing_key2;

    @Value("${spring.rabbitmq.queue3}")
    private String queue3;

    @Value("${spring.rabbitmq.exchange3}")
    private String exchange3;

    @Value("${spring.rabbitmq.routingKey3}")
    private String routing_key3;

    /*A Bean with name Queue is created, where the information from the exchange and sent to consumer */
    @Bean
    Queue queue2() {
        return new Queue(queue2, true);
    }

    /*A Bean with name exchange is created which routes the info. to the respective Queue.*/
    @Bean
    Exchange exchange2() {
        return ExchangeBuilder.directExchange(exchange2).durable(true).build();
    }

    /*A Bean with name Binding is created , which acts a link between a queue and exchange*/
    @Bean
    Binding binding2() {
        return BindingBuilder.bind(queue2())
                .to(exchange2())
                .with(routing_key2)
                .noargs();
    }

    @Bean
    Queue queue3() {
        return new Queue(queue3, true);
    }

    /*A Bean with name exchange is created which routes the info. to the respective Queue.*/
    @Bean
    Exchange exchange3() {
        return ExchangeBuilder.directExchange(exchange3).durable(true).build();
    }

    /*A Bean with name Binding is created , which acts a link between a queue and exchange*/
    @Bean
    Binding binding3() {
        return BindingBuilder.bind(queue3())
                .to(exchange3())
                .with(routing_key3)
                .noargs();
    }

    /*A Bean with name Queue is created, where the information from the exchange and sent to consumer */
    @Bean
    Queue queue() {
        return new Queue(queue1, true);
    }

    /*A Bean with name exchange is created which routes the info. to the respective Queue.*/
    @Bean
    Exchange exchange() {
        return ExchangeBuilder.directExchange(exchange1).durable(true).build();
    }

    /*A Bean with name Binding is created , which acts a link between a queue and exchange*/
    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routing_key1)
                .noargs();
    }
    /*A Bean with name ConnectionFactory is created, that helps in setting up a connection to rabbitmq server
    and configure binding to send the messages */
    @Bean
    CachingConnectionFactory connectionFactory2() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    /*A Bean with name MessageConverter is created, which converts java objects to JSON Format.*/
    @Bean
    Jackson2JsonMessageConverter messageConverter2() {
        return new Jackson2JsonMessageConverter();
    }

    /*A Bean with name RabbitTemplate is created, which accepts and forwards the messages*/
    @Bean
    RabbitTemplate rabbitTemplate2(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter2());
        return rabbitTemplate;
    }
}
