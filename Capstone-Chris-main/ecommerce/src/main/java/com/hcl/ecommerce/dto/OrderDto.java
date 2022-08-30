package com.hcl.ecommerce.dto;

import javax.validation.constraints.NotNull;

import com.hcl.ecommerce.entity.Address;
import com.hcl.ecommerce.entity.CreditCard;
import com.hcl.ecommerce.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
	
	private Integer id;
	
	@NotNull
	private User user;
	
	@NotNull
	private Address address;
	
	@NotNull
	private CreditCard creditCard;

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
