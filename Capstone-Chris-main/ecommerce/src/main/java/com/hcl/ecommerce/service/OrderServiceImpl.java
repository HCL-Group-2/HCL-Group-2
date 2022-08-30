package com.hcl.ecommerce.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dto.OrderDto;
import com.hcl.ecommerce.entity.Address;
import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.entity.CreditCard;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.OrderItem;
import com.hcl.ecommerce.entity.Payment;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.ShippingAddress;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.AddressRepository;
import com.hcl.ecommerce.repository.CartItemRepository;
import com.hcl.ecommerce.repository.CreditCardRepository;
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
	AddressRepository addressRepository;
	
	@Autowired
	CreditCardRepository creditCardRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	private MailSenderService mailSenderService;
	
//	@Override
//	public synchronized boolean addOrder(Order order) {
////		mailSenderService.sendEmail(order.getUser().getEmail());
////		try {
////			mailSenderService.sendEmailWithAttachment(order.getUser().getEmail());
////		} catch (MessagingException e) {
////		} catch (IOException e) {
////		}
//		orderRepository.save(order);
//		return true;
//	}
	
	@Override
	public synchronized boolean addOrder(OrderDto orderDto) {
		Order order = new Order();
		ShippingAddress shippingAddress = new ShippingAddress();
		Payment payment = new Payment();
		List<OrderItem> orderItems = new ArrayList<>();
		
		User user = getUserById(orderDto.getUser().getId());
		Address address = getAddressById(orderDto.getAddress().getId());
		CreditCard creditCard = getCreditCardById(orderDto.getCreditCard().getId());
		List<CartItem> cartItems = cartItemRepository.getAllCartItemsByUserId(orderDto.getUser().getId());
		
		BeanUtils.copyProperties(address, shippingAddress, "id");
		BeanUtils.copyProperties(creditCard, payment, "id");
		
		double total = 0.0;
		int inventory = 0;
		int quantity = 0;
		for (CartItem cartItem : cartItems) {
			Product prod = getProductById(cartItem.getProduct().getId());
			inventory = prod.getInventory();
			quantity = cartItem.getQuantity();
			if (inventory - quantity < 0) {
				cartItemRepository.delete(cartItem);
				continue;
			}
			prod.setInventory(inventory - quantity);
			productRepository.save(prod);
			OrderItem orderItem = new OrderItem();
			BeanUtils.copyProperties(cartItem, orderItem, "id");
			orderItem.setOrder(order);
			orderItems.add(orderItem);
			total += orderItem.getSubtotal();
		}
		
		order.setUser(user);
		order.setShippingAddress(shippingAddress);
		order.setPayment(payment);
		order.setOrderItems(orderItems);
		order.setOrderTotal(total);
		order.setOrderDate(LocalDate.now());
		order.setOrderStatus("Processing");
		
		cartItemRepository.deleteAll(cartItems);
		orderRepository.save(order);
		return true;
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
	
	@Override
	public CreditCard getCreditCardById(Integer creditCardId) {
		Optional<CreditCard> creditCard = creditCardRepository.findById(creditCardId);
		if (creditCard.isPresent())
			return creditCard.get();
		return null;
	}
	
	@Override
	public User getUserById(Integer userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent())
			return user.get();
		return null;
	}

	@Override
	public Address getAddressById(Integer addressId) {
		Optional<Address> address = addressRepository.findById(addressId);
		if (address.isPresent())
			return address.get();
		return null;
	}

	@Override
	public Order getOrderById(Integer orderId) {
		Optional<Order> order = orderRepository.findById(orderId);
		if (order.isPresent())
			return order.get();
		return null;
	}
	
	@Override
	public Product getProductById(Integer productId) {
		Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent()) return product.get();
		return null;
	}
	
	@Override
	public List<CartItem> getAllCartItems(Integer userId) {
		return cartItemRepository.getAllCartItemsByUserId(userId);
	}

}
