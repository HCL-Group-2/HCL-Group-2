package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.ecommerce.entity.CreditCard;
import com.hcl.ecommerce.service.CreditCardService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CreditCardControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	Jackson2ObjectMapperBuilder mapperBuilder;
	
	@MockBean
	CreditCardService creditCardService;
	
	@InjectMocks
	CreditCardController creditCardController;
	
	@Test
	public void testAddCreditCard() throws Exception {
		
		String mockCreditCardJson = 
				"{\"name\":\"Test User\",\"creditCardNumber\":\"1234123412341234\",\"expirationDate\":\"2024-01-01\",\"user\":{\"id\":1}}";
		
		ObjectMapper mapper = mapperBuilder.build();
		CreditCard mockCreditCard = mapper.readValue(mockCreditCardJson, CreditCard.class);
		
		Mockito.when(creditCardService.addCreditCard(any(CreditCard.class))).thenReturn(mockCreditCard);
		
		//Create a post request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/creditcard/")
				.accept(MediaType.APPLICATION_JSON).content(mockCreditCardJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		//Assert that the return status is CREATED
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
	}
	
	@Test
	public void testGetCreditCardById() throws Exception {
		
		String mockCreditCardJson = "{\"id\":1,\"name\":\"Test User\",\"creditCardNumber\":\"1234123412341234\",\"expirationDate\":\"2024-01-01\"}";
		
		ObjectMapper mapper = mapperBuilder.build();
		CreditCard mockCreditCard = mapper.readValue(mockCreditCardJson, CreditCard.class);
		
		Mockito.when(creditCardService.getCreditCardById(1)).thenReturn(mockCreditCard);
		
		//Create a request
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/creditcard/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "{\"id\":1,\"name\":\"Test User\",\"creditCardNumber\":\"1234123412341234\",\"expirationDate\":\"2024-01-01\"}";
		
		//Assert that response is what was expected
		assertEquals(expected, result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testUpdateCreditCard() throws Exception {
		
		String mockCreditCardJson = 
				"{\"id\":1,\"name\":\"Test User\",\"creditCardNumber\":\"4321432143214321\",\"expirationDate\":\"2024-01-01\"}";
		
		ObjectMapper mapper = mapperBuilder.build();
		CreditCard mockCreditCard = mapper.readValue(mockCreditCardJson, CreditCard.class);
		
		Mockito.when(creditCardService.updateCreditCard(any(CreditCard.class))).thenReturn(mockCreditCard);
		
		//Create a put request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/creditcard/")
				.accept(MediaType.APPLICATION_JSON).content(mockCreditCardJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "{\"id\":1,\"name\":\"Test User\",\"creditCardNumber\":\"4321432143214321\",\"expirationDate\":\"2024-01-01\"}";
		
		//Assert that response is what was expected
		assertEquals(expected, result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testDeleteCreditCard() throws Exception {
		
		String mockCreditCardJson = 
				"{\"id\":1,\"name\":\"Test User\",\"creditCardNumber\":\"4321432143214321\",\"expirationDate\":\"2024-01-01\"}";
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/creditcard/1")
				.accept(MediaType.APPLICATION_JSON).content(mockCreditCardJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		//Assert that the return status is 204 No Content
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
		
	}

}
