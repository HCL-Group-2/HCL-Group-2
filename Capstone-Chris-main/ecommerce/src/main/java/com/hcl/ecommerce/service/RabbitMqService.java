package com.hcl.ecommerce.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.entity.Product;

@Service
public class RabbitMqService {
	 private RabbitTemplate rabbitTemplate;
	    @Autowired
	    public RabbitMqService(RabbitTemplate rabbitTemplate) {
	        this.rabbitTemplate = rabbitTemplate;
	    }
	    @Value("${spring.rabbitmq.exchange}")
	    private String exchange;
	    @Value("${spring.rabbitmq.routingkey}")
	    private String routingkey;
	    public void send(Product product){
	        rabbitTemplate.convertAndSend(exchange,routingkey, product);
	    }
}
