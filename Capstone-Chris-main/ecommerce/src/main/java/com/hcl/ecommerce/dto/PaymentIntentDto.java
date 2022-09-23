package com.hcl.ecommerce.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentIntentDto {
	
	@NotNull
	private double orderTotal;
	
	@NotNull
	@Email
	private String email;
	

}
