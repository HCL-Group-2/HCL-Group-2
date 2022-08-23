package com.hcl.ecommerce.service;

import com.hcl.ecommerce.entity.Cart;

public interface CartService {
	
	boolean addCart(Cart cart);

	Cart getCartById(Integer cartId);

	void updateCart(Cart cart);

	void deleteCart(Integer cartId);

}
