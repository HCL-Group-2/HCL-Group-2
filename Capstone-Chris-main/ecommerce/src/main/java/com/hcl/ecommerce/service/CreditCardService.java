package com.hcl.ecommerce.service;

import com.hcl.ecommerce.entity.CreditCard;

public interface CreditCardService {

	boolean addCreditCard(CreditCard creditCard);

	CreditCard getCreditCardById(Integer creditCardId);

	void updateCreditCard(CreditCard creditCard);

	void deleteCreditCard(Integer creditCardId);

}
