package com.hcl.ecommerce.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CartItemDto {
	
	private Integer id;
	
	@NotNull
	@Size(min = 0)
	private int quantity;
	
	private BigDecimal subtotal;
	
	@NotNull
	private UserDto userDto;
	
	@NotNull
	private ProductDto productDto;
	


}
