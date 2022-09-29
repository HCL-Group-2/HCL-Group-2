package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.dto.PaymentInfoDTO;
import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.exception.AddEntityException;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface OrderService {
	
	Order addOrder(Order order) throws AddEntityException;

	Order getOrderById(Integer orderId);
	
	Order updateOrder(Order order);

	void deleteOrder(Integer orderId);
	
	Product getProductById(Integer productId);

	List<CartItem> getAllCartItemsByUserId(Integer userId);
	
    PaymentIntent createPaymentIntent(PaymentInfoDTO paymentInfo) throws StripeException;


}
