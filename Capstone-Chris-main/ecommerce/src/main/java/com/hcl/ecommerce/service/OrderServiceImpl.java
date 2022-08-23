package com.hcl.ecommerce.service;

import java.io.IOException;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	private MailSenderService mailSenderService;
	
	@Override
	public synchronized boolean addOrder(Order order) {
//		mailSenderService.sendEmail(order.getUser().getEmail());
//		try {
//			mailSenderService.sendEmailWithAttachment(order.getUser().getEmail());
//		} catch (MessagingException e) {
//		} catch (IOException e) {
//		}
		orderRepository.save(order);
		return true;
	}

	@Override
	public Order getOrderById(Integer orderId) {
		Optional<Order> order = orderRepository.findById(orderId);
		if (order.isPresent())
			return order.get();
		return null;
	}

	@Override
	public void updateOrder(Order order) {
		Order ord = getOrderById(order.getId());
		ord.setOrderDate(order.getOrderDate());
		ord.setOrderTotal(order.getOrderTotal());
		ord.setOrderStatus(order.getOrderStatus());
		orderRepository.save(ord);
	}

	@Override
	public void deleteOrder(Integer orderId) {
		orderRepository.deleteById(orderId);
	}

}
