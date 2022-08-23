package com.hcl.ecommerce.service;

import com.hcl.ecommerce.entity.CartItem;

public interface CartItemService {
	
	boolean addCartItem(CartItem cartItem);

	CartItem getCartItemById(Integer cartItemId);

	void updateCartItem(CartItem cartItem);

	void deleteCartItem(Integer cartItemId);

}
