package com.stackroute.userservice;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {

//	@Value("${spring.rabbitmq.host}")
//	String host;
//	@Value("${spring.rabbitmq.username}")
//	String username;
//	@Value("${spring.rabbitmq.password}")
//	String password;
	public static void main(String[] args) {

		SpringApplication.run(UserServiceApplication.class, args);
	}
//	@Bean
//	CachingConnectionFactory connectionFactory() {
//		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
//		cachingConnectionFactory.setUsername(username);
//		cachingConnectionFactory.setPassword(password);
//		return cachingConnectionFactory;
//	}
//	@Bean
//	public Jackson2JsonMessageConverter jsonMessageConverter() {
//		return new Jackson2JsonMessageConverter();
//	}
//	@Bean
//	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//		rabbitTemplate.setMessageConverter(jsonMessageConverter());
//		return rabbitTemplate;
//	}

//	@Value("${spring.rabbitmq.queue1}")
//	private String queue1;
//
//	@Value("${spring.rabbitmq.exchange1}")
//	private String exchange1;
//
//	@Value("${spring.rabbitmq.routingkey1}")
//	private String routing_key1;
//
//	/*A Bean with name Queue is created, where the information from the exchange and sent to consumer */
//	@Bean
//	Queue queue2() {
//		return new Queue(queue1, true);
//	}
//
//	/*A Bean with name exchange is created which routes the info. to the respective Queue.*/
//	@Bean
//	Exchange exchange2() {
//		return ExchangeBuilder.directExchange(exchange1).durable(true).build();
//	}
//
//	/*A Bean with name Binding is created , which acts a link between a queue and exchange*/
//	@Bean
//	Binding binding2() {
//		return BindingBuilder.bind(queue2())
//				.to(exchange2())
//				.with(routing_key1)
//				.noargs();
//	}
}
