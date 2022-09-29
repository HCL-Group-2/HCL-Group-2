package com.hcl.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.service.RabbitMqService;

@RestController
@RequestMapping(value = "/api/v1")
public class MessageController {
	 private RabbitMqService rabbitMqService;
	    @Autowired
	    public MessageController(RabbitMqService rabbitMqSender) {
	        this.rabbitMqService = rabbitMqSender;
	    }
	    @Value("${app.message}")
	    private String message;
	    @PostMapping(value = "product")
	    public String publishProductDetails(@RequestBody Product product) {
	        rabbitMqService.send(product);
	        return message;
	    }
}
