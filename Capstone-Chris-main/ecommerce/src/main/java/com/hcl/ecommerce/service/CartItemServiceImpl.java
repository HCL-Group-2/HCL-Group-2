package com.hcl.ecommerce.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.AddEntityException;
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
	public synchronized CartItem addCartItem(CartItem cartItem) throws AddEntityException {
		User user = getUserById(cartItem.getUser().getId());
		Product product = getProductById(cartItem.getProduct().getId());
		if (user == null) {
			throw new AddEntityException("The user doesn't exists");
		}
		if (product == null) {
			throw new AddEntityException("The product doesn't exists");
		}
		if (user.getCartItems() != null && user.getCartItems().contains(cartItem)) {
			List<CartItem> items = user.getCartItems();
			Integer cartItemId = items.get(items.indexOf(cartItem)).getId();
			CartItem item = getCartItemById(cartItemId);
			int currentQuantity = item.getQuantity();
			item.setQuantity(currentQuantity + cartItem.getQuantity());
			BigDecimal subtotal = product.getPrice().multiply(new BigDecimal(item.getQuantity()));
			item.setSubtotal(subtotal);
			item.setProduct(product);
			return cartItemRepository.save(item);
		}
		BigDecimal subtotal = product.getPrice().multiply(new BigDecimal(cartItem.getQuantity()));
		cartItem.setSubtotal(subtotal);
		cartItem.setUser(user);
		cartItem.setProduct(product);
		return cartItemRepository.save(cartItem);
	}
	
	@Override
	public CartItem getCartItemById(Integer cartItemId) {
		Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
		if (cartItem.isPresent())
			return cartItem.get();
		return null;
	}
	
	@Override
	public CartItem updateCartItem(CartItem cartItem) {
		CartItem item = getCartItemById(cartItem.getId());
		item.setQuantity(cartItem.getQuantity());
		item.setSubtotal(cartItem.getSubtotal());
		return cartItemRepository.save(item);
	}
	
	@Override
	public void deleteCartItem(Integer cartItemId) {
		cartItemRepository.deleteById(cartItemId);
	}
	
	@Override
	public List<CartItem> getAllCartItemsByUserId(Integer userId) {
		return cartItemRepository.getAllCartItemsByUserId(userId);
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

}
