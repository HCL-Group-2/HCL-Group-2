package com.hcl.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dto.CartItemDto;
import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.repository.CartItemRepository;

@Service
public class CartItemServiceImpl implements CartItemService {
	
	@Autowired
	CartItemRepository cartItemRepository;
	
//	@Override
//	public synchronized boolean addCartItem(CartItem cartItem) {
//		cartItemRepository.save(cartItem);
//		return true;
//	}
	
	@Override
	public synchronized boolean addCartItem(CartItemDto cartItemDto) {
		CartItem cartItem = new CartItem();
		BeanUtils.copyProperties(cartItemDto, cartItem);
		cartItemRepository.save(cartItem);
		cartItemDto.setId(cartItem.getId());
		return true;
	}
	
//	@Override
//	public CartItem getCartItemByUserIdAndProductId(Integer userId, Integer productId) {
//		Optional<CartItem> cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);
//		if (cartItem.isPresent())
//			return cartItem.get();
//		return null;
//	}
	
	@Override
	public CartItem getCartItemById(Integer cartItemId) {
		Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
		if (cartItem.isPresent())
			return cartItem.get();
		return null;
	}
	
//	@Override
//	public void updateCartItem(CartItem cartItem) {
//		CartItem item = getCartItemByUserIdAndProductId(cartItem.getCartItemId().getUserId(), cartItem.getCartItemId().getProductId());
//		item.setQuantity(cartItem.getQuantity());
//		cartItemRepository.save(item);
//	}
	
	@Override
	public void updateCartItem(CartItem cartItem) {
		CartItem item = getCartItemById(cartItem.getId());
		item.setQuantity(cartItem.getQuantity());
		cartItemRepository.save(item);
	}
	
//	@Override
//	public void deleteCartItem(Integer userId, Integer productId) {
//		CartItem item = getCartItemByUserIdAndProductId(userId, productId);
//		cartItemRepository.delete(item);
//	}
	
	@Override
	public void deleteCartItem(Integer cartItemId) {
		cartItemRepository.deleteById(cartItemId);
	}
	
	@Override
	public List<CartItem> getAllCartItems() {
		return cartItemRepository.getAllCartItems();
	}

}
