package com.hcl.ecommerce.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.OrderItem;
import com.hcl.ecommerce.entity.Payment;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.ShippingAddress;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.repository.CartItemRepository;
import com.hcl.ecommerce.repository.OrderRepository;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired
	ProductRepository productRepository;
	
//	@Autowired
//	private MailSenderService mailSenderService;
	
	@Override
	public synchronized Order addOrder(Order order) throws AddEntityException {
		User user = userRepository.findByEmail(order.getUser().getEmail());
		if (user != null) {		
			List<CartItem> cartItems = cartItemRepository.getAllCartItemsByUserId(user.getId());
			List<OrderItem> orderItems = new ArrayList<>();
			double total = 0.0;
			for (CartItem cartItem : cartItems) {
				Product prod = getProductById(cartItem.getProduct().getId());
				prod.setInventory(prod.getInventory() - cartItem.getQuantity());
				productRepository.save(prod);
				OrderItem orderItem = new OrderItem();
				BeanUtils.copyProperties(cartItem, orderItem, "id");
				orderItem.setOrder(order);
				orderItems.add(orderItem);
				total += orderItem.getSubtotal();
			}
			
			order.setUser(user);
			order.setOrderItems(orderItems);
			order.setOrderTotal(total);
			order.setOrderDate(LocalDate.now());
			order.setOrderStatus("In Progress");
			
			cartItemRepository.deleteAll(cartItems);
			
//			mailSenderService.sendEmail(order.getUser().getEmail());
//			try {
//				mailSenderService.sendEmailWithAttachment(order.getUser().getEmail());
//			} catch (MessagingException e) {
//			} catch (IOException e) {
//			}
			return orderRepository.save(order);
		} else {
			return null;
		}
	}
	
	@Override
	public Order getOrderById(Integer orderId) {
		Optional<Order> order = orderRepository.findById(orderId);
		if (order.isPresent())
			return order.get();
		return null;
	}

	@Override
	public void deleteOrder(Integer orderId) {
		orderRepository.deleteById(orderId);
	}
	
	@Override
	public Product getProductById(Integer productId) {
		Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent()) return product.get();
		return null;
	}
	
	@Override
	public List<CartItem> getAllCartItemsByUserId(Integer userId) {
		return cartItemRepository.getAllCartItemsByUserId(userId);
	}

}
