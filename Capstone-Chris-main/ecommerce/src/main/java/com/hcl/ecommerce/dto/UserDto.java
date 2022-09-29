package com.hcl.ecommerce.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private Integer id;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@Email
	private String email;

	private List<CartItemDto> cartItems;
	
	private List<OrderDto> orders;

}
