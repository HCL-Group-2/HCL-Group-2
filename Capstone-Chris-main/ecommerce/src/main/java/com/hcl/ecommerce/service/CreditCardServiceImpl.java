package com.hcl.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.entity.CreditCard;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.repository.CreditCardRepository;

@Service
public class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	CreditCardRepository creditCardRepository;

	@Override
	public synchronized CreditCard addCreditCard(CreditCard creditCard) throws AddEntityException {
		// Check if credit card already exist for user
		return creditCardRepository.save(creditCard);
	}

	@Override
	public CreditCard getCreditCardById(Integer creditCardId) {
		Optional<CreditCard> creditCard = creditCardRepository.findById(creditCardId);
		if (creditCard.isPresent())
			return creditCard.get();
		return null;
	}

	@Override
	public CreditCard updateCreditCard(CreditCard creditCard) {
		CreditCard cc = getCreditCardById(creditCard.getId());
		cc.setName(creditCard.getName());
		cc.setCreditCardNumber(creditCard.getCreditCardNumber());
		cc.setExpirationDate(creditCard.getExpirationDate());
		return creditCardRepository.save(cc);
	}

	@Override
	public void deleteCreditCard(Integer creditCardId) {
		creditCardRepository.deleteById(creditCardId);
	}

	@Override
	public List<CreditCard> getAllCreditCardsByUserId(Integer userId) {
		return creditCardRepository.getAllCreditCards(userId);
	}

}
