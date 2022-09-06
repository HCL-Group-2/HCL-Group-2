package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.AddEntityException;

public interface CartItemService {
	
	CartItem addCartItem(CartItem cartItem) throws AddEntityException;
	
	CartItem getCartItemById(Integer cartItemId);
	
	void updateCartItem(CartItem cartItem);
	
	void deleteCartItem(Integer cartItemId);

	List<CartItem> getAllCartItemsByUserId(Integer userId);

	User getUserById(Integer userId);

	Product getProductById(Integer productId);

}
