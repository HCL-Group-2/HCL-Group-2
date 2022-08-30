package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.dto.OrderDto;
import com.hcl.ecommerce.entity.Address;
import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.entity.CreditCard;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;

public interface OrderService {
	
	boolean addOrder(OrderDto orderDto);

	Order getOrderById(Integer orderId);

	void updateOrder(Order order);

	void deleteOrder(Integer orderId);
	
	User getUserById(Integer userId);

	CreditCard getCreditCardById(Integer creditCardId);

	Address getAddressById(Integer addressId);
	
	Product getProductById(Integer productId);

	List<CartItem> getAllCartItems(Integer userId);

}
