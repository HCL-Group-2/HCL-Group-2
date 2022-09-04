package com.hcl.ecommerce.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.ecommerce.entity.CreditCard;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.CreditCardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardServiceImplTest {
	
	@InjectMocks
	CreditCardServiceImpl creditCardServiceImpl;

	@Mock
	CreditCardRepository creditCardRepository;
	
	@Test
	public void testAddUser() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("testuser@gmail.com");
		user.setPassword("test");
		
		CreditCard mockCreditCard = new CreditCard();
		mockCreditCard.setId(1);
		mockCreditCard.setName("Test Name");
		mockCreditCard.setCreditCardNumber("1234123412341234");
		mockCreditCard.setExpirationDate("2024-01-01");
		mockCreditCard.setUser(user);
		
		Mockito.when(creditCardRepository.save(any(CreditCard.class))).thenReturn(mockCreditCard);
		
		CreditCard creditCard = creditCardServiceImpl.addCreditCard(mockCreditCard);
		
		assertNotNull(creditCard);
		
		assertEquals("Test Name", creditCard.getName());
		assertEquals("1234123412341234", creditCard.getCreditCardNumber());
		assertEquals("2024-01-01", creditCard.getExpirationDate());
		
	}
	
	@Test
	public void testGetUserById() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("testuser@gmail.com");
		user.setPassword("test");
		
		CreditCard mockCreditCard = new CreditCard();
		mockCreditCard.setId(1);
		mockCreditCard.setName("Test Name");
		mockCreditCard.setCreditCardNumber("1234123412341234");
		mockCreditCard.setExpirationDate("2024-01-01");
		mockCreditCard.setUser(user);
		
		Mockito.when(creditCardRepository.findById(1)).thenReturn(Optional.of(mockCreditCard));
		
		CreditCard creditCard = creditCardServiceImpl.getCreditCardById(1);
		
		assertNotNull(creditCard);
		
		assertEquals("Test Name", creditCard.getName());
		assertEquals("1234123412341234", creditCard.getCreditCardNumber());
		assertEquals("2024-01-01", creditCard.getExpirationDate());
		
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("testuser@gmail.com");
		user.setPassword("test");
		
		CreditCard mockCreditCard = new CreditCard();
		mockCreditCard.setId(1);
		mockCreditCard.setName("Test Name");
		mockCreditCard.setCreditCardNumber("1234123412341234");
		mockCreditCard.setExpirationDate("2024-01-01");
		mockCreditCard.setUser(user);
		
		Mockito.when(creditCardRepository.findById(1)).thenReturn(Optional.of(mockCreditCard));
		
		Mockito.when(creditCardRepository.save(any(CreditCard.class))).thenReturn(mockCreditCard);
		
		CreditCard creditCard = creditCardServiceImpl.updateCreditCard(mockCreditCard);
		
		assertNotNull(creditCard);
		
		assertEquals("Test Name", creditCard.getName());
		assertEquals("1234123412341234", creditCard.getCreditCardNumber());
		assertEquals("2024-01-01", creditCard.getExpirationDate());
		
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("testuser@gmail.com");
		user.setPassword("test");
		
		CreditCard mockCreditCard = new CreditCard();
		mockCreditCard.setId(1);
		mockCreditCard.setName("Test Name");
		mockCreditCard.setCreditCardNumber("1234123412341234");
		mockCreditCard.setExpirationDate("2024-01-01");
		mockCreditCard.setUser(user);
		
		creditCardServiceImpl.deleteCreditCard(1);
		
		Mockito.verify(creditCardRepository, times(1)).deleteById(1);
		
	}

}
