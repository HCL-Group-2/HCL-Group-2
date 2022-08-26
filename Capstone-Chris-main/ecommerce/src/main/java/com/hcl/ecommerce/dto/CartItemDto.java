package com.hcl.ecommerce.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
	
	private Integer id;
	
	@NotNull
	@Size(min = 0)
	private int quantity;
	
	@NotNull
	private User user;
	
	@NotNull
	private Product product;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	

}
