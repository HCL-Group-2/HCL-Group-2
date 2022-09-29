package com.hcl.ecommerce.service;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;

import com.hcl.ecommerce.entity.Product;

@Component
public class RabbitMqReceiver implements RabbitListenerConfigurer {
    private static final org.jboss.logging.Logger logger = LoggerFactory.logger(RabbitMqReceiver.class);
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(Product product) {
        logger.info("User Details Received is.. " + product);
    }
}
