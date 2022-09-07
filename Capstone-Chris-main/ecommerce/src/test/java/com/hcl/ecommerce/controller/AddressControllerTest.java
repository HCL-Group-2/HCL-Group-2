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
import com.hcl.ecommerce.entity.Address;
import com.hcl.ecommerce.service.AddressService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	Jackson2ObjectMapperBuilder mapperBuilder;
	
	@MockBean
	AddressService addressService;
	
	@InjectMocks
	AddressController addressController;
	
	@Test
	public void testAddAddress() throws Exception {
		
		String mockAddressJson = 
				"{\"address1\":\"123 Test Address\",\"address2\":null,\"city\":\"Frisco\",\"state\":\"Texas\",\"zipCode\":\"75034\",\"user\":{\"id\":1}}";
		
		ObjectMapper mapper = mapperBuilder.build();
		Address mockAddress = mapper.readValue(mockAddressJson, Address.class);
		
		Mockito.when(addressService.addAddress(any(Address.class))).thenReturn(mockAddress);
		
		//Create a post request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/address/")
				.accept(MediaType.APPLICATION_JSON).content(mockAddressJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		//Assert that the return status is CREATED
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
	}
	
	@Test
	public void testGetAddressById() throws Exception {
		
		String mockAddressJson = "{\"id\":1,\"address1\":\"123 Test Address\",\"address2\":null,\"city\":\"Frisco\",\"state\":\"Texas\",\"zipCode\":\"75034\"}";
		
		ObjectMapper mapper = mapperBuilder.build();
		Address mockAddress = mapper.readValue(mockAddressJson, Address.class);
		
		Mockito.when(addressService.getAddressById(1)).thenReturn(mockAddress);
		
		//Create a request
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/address/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "{\"id\":1,\"address1\":\"123 Test Address\",\"address2\":null,\"city\":\"Frisco\",\"state\":\"Texas\",\"zipCode\":\"75034\"}";
		
		//Assert that response is what was expected
		assertEquals(expected, result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testUpdateAddress() throws Exception {
		
		String mockAddressJson = 
				"{\"id\":1,\"address1\":\"123 Test Address Updated\",\"address2\":null,\"city\":\"Frisco\",\"state\":\"Texas\",\"zipCode\":\"75034\"}";
		
		ObjectMapper mapper = mapperBuilder.build();
		Address mockAddress = mapper.readValue(mockAddressJson, Address.class);
		
		Mockito.when(addressService.updateAddress(any(Address.class))).thenReturn(mockAddress);
		
		//Create a put request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/address/")
				.accept(MediaType.APPLICATION_JSON).content(mockAddressJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "{\"id\":1,\"address1\":\"123 Test Address Updated\",\"address2\":null,\"city\":\"Frisco\",\"state\":\"Texas\",\"zipCode\":\"75034\"}";
		
		//Assert that response is what was expected
		assertEquals(expected, result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testDeleteAddress() throws Exception {
		
		String mockAddressJson = 
				"{\"id\":1,\"address1\":\"123 Test Address Updated\",\"address2\":null,\"city\":\"Frisco\",\"state\":\"Texas\",\"zipCode\":\"75034\"}";
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/address/1")
				.accept(MediaType.APPLICATION_JSON).content(mockAddressJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		//Assert that the return status is 204 No Content
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
		
	}

}
