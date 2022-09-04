package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.ecommerce.entity.CreditCard;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.service.CreditCardService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardControllerTest {
	
	@InjectMocks
	CreditCardController creditCardController;
	
	@Mock
	CreditCardService creditCardService;
	
	@Test
	public void testAddCreditCard() throws Exception {
		
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
		
		Mockito.when(creditCardService.addCreditCard(any(CreditCard.class))).thenReturn(mockCreditCard);
		
		ResponseEntity<CreditCard> response = creditCardController.addCreditCard(mockCreditCard);
		
		CreditCard creditCard = response.getBody();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());
		
		assertEquals("Test Name", creditCard.getName());
		assertEquals("1234123412341234", creditCard.getCreditCardNumber());
		assertEquals("2024-01-01", creditCard.getExpirationDate());
		
	}
	
	@Test
	public void testGetCreditCardById() throws Exception {
		
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
		
		Mockito.when(creditCardService.getCreditCardById(1)).thenReturn(mockCreditCard);
		
		ResponseEntity<CreditCard> response = creditCardController.getCreditCardById(1);
		
		CreditCard creditCard = response.getBody();
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
		assertEquals("Test Name", creditCard.getName());
		assertEquals("1234123412341234", creditCard.getCreditCardNumber());
		assertEquals("2024-01-01", creditCard.getExpirationDate());
		
	}
	
	@Test
	public void testUpdateCreditCard() throws Exception {
		
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
		
		Mockito.when(creditCardService.updateCreditCard(any(CreditCard.class))).thenReturn(mockCreditCard);
		
		ResponseEntity<CreditCard> response = creditCardController.updateCreditCard(mockCreditCard);
		
		CreditCard creditCard = response.getBody();
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
		assertEquals("Test Name", creditCard.getName());
		assertEquals("1234123412341234", creditCard.getCreditCardNumber());
		assertEquals("2024-01-01", creditCard.getExpirationDate());
		
	}
	
	@Test
	public void testDeleteCreditCard() throws Exception {
		
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
		
		Mockito.when(creditCardService.deleteCreditCard(1)).thenReturn("Success");
		
		ResponseEntity<String> response = creditCardController.deleteCreditCard(1);
		
		String str = response.getBody();
		
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCodeValue());
		
		assertEquals("Success", str);
		
	}

}
