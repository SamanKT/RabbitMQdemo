package com.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan("com.*")
@RestController

public class RabbitmqDemoApplication {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("my-rabbit.queue-name")
	private String queueName;

	@Value("my-rabbit.exchange-name")
	private String exchangeName;

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqDemoApplication.class, args);
	}

	@RequestMapping("/")
	public Object sendMessage() {
		Object convertSendAndReceive = rabbitTemplate.convertSendAndReceive(exchangeName, "samy.haji", "Hello World");

		return convertSendAndReceive;
	}
}
