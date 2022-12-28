package com.rabbitmq.receiver;

import org.springframework.stereotype.Component;

@Component
public class Receiver {
	
	private String name;
	
	public Receiver() {
	
	}

	public void printMessage(String message) {
		
		System.out.println("the received message is: " + message);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
