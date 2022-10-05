package com.hcl.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stripe.Stripe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
public class StripeDTO {
	@Value("${stripe.key.secret}") 
	private String secretKey;
	
//	StripeDTO() {
//	
//		// initialize Stripe API with secret key
//		System.out.println("secret key is " + secretKey);
//		Stripe.apiKey = secretKey;
//	}

}
