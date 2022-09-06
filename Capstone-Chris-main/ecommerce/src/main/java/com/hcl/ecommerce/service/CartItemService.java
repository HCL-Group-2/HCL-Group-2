package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;

public interface CartItemService {
	
	boolean addCartItem(CartItem cartItem);
	
	User getUserById(Integer userId);
	
	Product getProductById(Integer productId);
	
	CartItem getCartItemById(Integer cartItemId);
	
	void updateCartItem(CartItem cartItem);
	
	void deleteCartItem(Integer cartItemId);

	List<CartItem> getAllCartItemsByUserId(Integer userId);

}
