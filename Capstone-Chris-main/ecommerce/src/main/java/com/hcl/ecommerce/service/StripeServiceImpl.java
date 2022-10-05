package com.hcl.ecommerce.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.stripe.Stripe;



@Service
public class StripeServiceImpl {
	
	
	public StripeServiceImpl(@Value("${stripe.key.secret}") String secretKey) {

		// initialize Stripe API with secret key
		Stripe.apiKey = secretKey;
	}
	

}
