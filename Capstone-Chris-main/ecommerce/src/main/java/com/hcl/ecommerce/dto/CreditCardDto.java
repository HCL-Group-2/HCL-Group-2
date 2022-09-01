package com.hcl.ecommerce.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCardDto {
	
	@NotNull
	private String creditCardNumber;
	
	@NotNull
	private String expirationDate;
	
	@NotNull
	private String verificationNumber;

}
