package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.entity.CartItem;

public interface CartItemService {
	
	boolean addCartItem(CartItem cartItem);
	
//	CartItem getCartItemByUserIdAndProductId(Integer userId, Integer productId);
	
	CartItem getCartItemById(Integer cartItemId);

//	void updateCartItem(CartItem cartItem);
	
	void updateCartItem(CartItem cartItem);
	
//	void deleteCartItem(Integer userId, Integer productId);
	
	void deleteCartItem(Integer cartItemId);
	
	List<CartItem> getAllCartItems();

}
