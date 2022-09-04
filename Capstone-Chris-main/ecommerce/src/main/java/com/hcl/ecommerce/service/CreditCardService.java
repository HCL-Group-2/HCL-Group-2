package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.entity.CreditCard;
import com.hcl.ecommerce.exception.AddEntityException;

public interface CreditCardService {

	CreditCard addCreditCard(CreditCard creditCard) throws AddEntityException;

	CreditCard getCreditCardById(Integer creditCardId);

	CreditCard updateCreditCard(CreditCard creditCard);

	String deleteCreditCard(Integer creditCardId);

	List<CreditCard> getAllCreditCardsByUserId(Integer userId);

}
