package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.entity.CreditCard;
import com.hcl.ecommerce.exception.AddEntityException;

public interface CreditCardService {

	CreditCard addCreditCard(CreditCard creditCard) throws AddEntityException;

	CreditCard getCreditCardById(Integer creditCardId);

	void updateCreditCard(CreditCard creditCard);

	void deleteCreditCard(Integer creditCardId);

	List<CreditCard> getAllCreditCardsByUserId(Integer userId);

}
