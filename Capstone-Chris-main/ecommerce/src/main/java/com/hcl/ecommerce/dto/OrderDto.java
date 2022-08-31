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

}
