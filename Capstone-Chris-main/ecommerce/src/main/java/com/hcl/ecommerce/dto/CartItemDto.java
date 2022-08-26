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

}
