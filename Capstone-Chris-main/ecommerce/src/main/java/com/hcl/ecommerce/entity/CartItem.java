package com.hcl.ecommerce.entity;

import java.math.BigDecimal;
import java.util.Objects;

import com.hcl.ecommerce.dto.CartItemDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "cart_items")
public class CartItem {
	
	public CartItem(CartItemDto dto) {
		id = dto.getId();
		quantity = dto.getQuantity();
		subtotal = dto.getSubtotal();
		user = new User(dto.getUserDto());
		product = new Product(dto.getProductDto());
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_item_id")
	private Integer id;
	
	@Column(nullable = false)
	private int quantity;
	
	private BigDecimal subtotal;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	public CartItemDto toDto() {
		return new CartItemDto(id, quantity, subtotal, user.toDto(), product.toDto());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(product.getId(), user.getId());
	}

	@Override
	public boolean equals(Object o) {
		CartItem item = null;
		if (o instanceof CartItem) {
			item = (CartItem) o;
		} else {
			return false;
		}
		
		if ((this.user.getId() == item.getUser().getId()) && (this.product.getId() == item.getProduct().getId())) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
