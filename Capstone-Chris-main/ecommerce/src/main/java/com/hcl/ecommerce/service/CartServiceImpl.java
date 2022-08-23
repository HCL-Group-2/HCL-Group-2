package com.hcl.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.entity.Cart;
import com.hcl.ecommerce.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartRepository cartRepository;
	
	@Override
	public synchronized boolean addCart(Cart cart) {
		cartRepository.save(cart);
		return true;
	}

	@Override
	public Cart getCartById(Integer cartId) {
		Optional<Cart> cart = cartRepository.findById(cartId);
		if (cart.isPresent())
			return cart.get();
		return null;
	}

	@Override
	public void updateCart(Cart cart) {
		Cart crt = getCartById(cart.getId());
		cartRepository.save(crt);
	}

	@Override
	public void deleteCart(Integer cartId) {
		cartRepository.deleteById(cartId);
	}

}
