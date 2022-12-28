package com.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.receiver.Receiver;

@Configuration
public class RabbitMQconfig {
	
	
	@Value("my-rabbit.queue-name")
	private String queueName;
	
	@Value("my-rabbit.exchange-name")
	private String exchangeName;
	
	
	
	@Bean
	public Queue queue() {
		
		return new Queue(queueName,false);
	}
	
	@Bean
	public Exchange myExchange() {
		
		return new TopicExchange(exchangeName);
	}
	
	
	@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter messageListener) {
		
		SimpleMessageListenerContainer containerObj = new SimpleMessageListenerContainer(connectionFactory);
		containerObj.setQueueNames(queueName);
		containerObj.setMessageListener(messageListener);
		return containerObj;
	}

	@Bean
	public MessageListenerAdapter messageListener(Receiver receiver) {
		
		return new MessageListenerAdapter(receiver, "printMessage");  // run the printMessage method on receiving message
	}
	
	@Bean
	public Binding binding(TopicExchange exchange, Queue queue) {
		
		return BindingBuilder.bind(queue).to(exchange).with("samy.#");
	}

}
