package com.hcl.ecommerce.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class MessageController {
	private final RabbitTemplate rabbitTemplate;
	static final String topicExchangeName = "spring-boot-exchange";

	public MessageController(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	@GetMapping("hello")
	public void hello() {
		System.out.println("Sending message...");
		rabbitTemplate.convertAndSend(topicExchangeName, "foo.bar.baz", "Message from RabbitMQ");
	}
}
