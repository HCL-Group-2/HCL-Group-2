package com.hcl.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.CartItemRepository;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.repository.UserRepository;

@Service
public class CartItemServiceImpl implements CartItemService {
	
	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public synchronized boolean addCartItem(CartItem cartItem) {
		User user = getUserById(cartItem.getUser().getId());
		Product product = getProductById(cartItem.getProduct().getId());
		double subtotal = product.getPrice() * cartItem.getQuantity();
		cartItem.setSubtotal(subtotal);
		cartItem.setUser(user);
		cartItem.setProduct(product);
		cartItemRepository.save(cartItem);
		return true;
	}
	
	@Override
	public User getUserById(Integer userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent())
			return user.get();
		return null;
	}
	
	@Override
	public Product getProductById(Integer productId) {
		Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent()) return product.get();
		return null;
	}
	
	@Override
	public CartItem getCartItemById(Integer cartItemId) {
		Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
		if (cartItem.isPresent())
			return cartItem.get();
		return null;
	}
	
	@Override
	public void updateCartItem(CartItem cartItem) {
		CartItem item = getCartItemById(cartItem.getId());
		User user = getUserById(cartItem.getUser().getId());
		Product product = getProductById(cartItem.getProduct().getId());
		item.setQuantity(cartItem.getQuantity());
		double subtotal = product.getPrice() * cartItem.getQuantity();
		item.setSubtotal(subtotal);
		item.setUser(user);
		item.setProduct(product);
		cartItemRepository.save(item);
	}
	
	@Override
	public void deleteCartItem(Integer cartItemId) {
		cartItemRepository.deleteById(cartItemId);
	}
	
	@Override
	public List<CartItem> getAllCartItemsByUserId(Integer userId) {
		return cartItemRepository.getAllCartItemsByUserId(userId);
	}

}
