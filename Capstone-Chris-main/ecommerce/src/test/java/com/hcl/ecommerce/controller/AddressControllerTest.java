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

import com.hcl.ecommerce.entity.Address;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.service.AddressService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressControllerTest {
	
	@InjectMocks
	AddressController addressController;
	
	@Mock
	AddressService addressService;
	
	@Test
	public void testAddAddress() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("testuser@gmail.com");
		user.setPassword("test");
		
		Address mockAddress = new Address();
		mockAddress.setId(1);
		mockAddress.setAddress1("123 Test Address");
		mockAddress.setAddress2(null);
		mockAddress.setCity("Frisco");
		mockAddress.setState("Texas");
		mockAddress.setZipCode("75034");
		mockAddress.setUser(user);
		
		Mockito.when(addressService.addAddress(any(Address.class))).thenReturn(mockAddress);
		
		ResponseEntity<Address> response = addressController.addAddress(mockAddress);
		
		Address address = response.getBody();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());
		
		assertEquals("123 Test Address", address.getAddress1());
		assertEquals(null, address.getAddress2());
		assertEquals("Frisco", address.getCity());
		assertEquals("Texas", address.getState());
		assertEquals("75034", address.getZipCode());
		
	}
	
	@Test
	public void testGetAddressById() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("testuser@gmail.com");
		user.setPassword("test");
		
		Address mockAddress = new Address();
		mockAddress.setId(1);
		mockAddress.setAddress1("123 Test Address");
		mockAddress.setAddress2(null);
		mockAddress.setCity("Frisco");
		mockAddress.setState("Texas");
		mockAddress.setZipCode("75034");
		mockAddress.setUser(user);
		
		Mockito.when(addressService.getAddressById(1)).thenReturn(mockAddress);
		
		ResponseEntity<Address> response = addressController.getAddressById(1);
		
		Address address = response.getBody();
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
		assertEquals("123 Test Address", address.getAddress1());
		assertEquals(null, address.getAddress2());
		assertEquals("Frisco", address.getCity());
		assertEquals("Texas", address.getState());
		assertEquals("75034", address.getZipCode());
		
	}
	
	@Test
	public void testUpdateAddress() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("testuser@gmail.com");
		user.setPassword("test");
		
		Address mockAddress = new Address();
		mockAddress.setId(1);
		mockAddress.setAddress1("123 Test Address");
		mockAddress.setAddress2(null);
		mockAddress.setCity("Frisco");
		mockAddress.setState("Texas");
		mockAddress.setZipCode("75034");
		mockAddress.setUser(user);
		
		Mockito.when(addressService.updateAddress(any(Address.class))).thenReturn(mockAddress);
		
		ResponseEntity<Address> response = addressController.updateAddress(mockAddress);
		
		Address address = response.getBody();
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
		assertEquals("123 Test Address", address.getAddress1());
		assertEquals(null, address.getAddress2());
		assertEquals("Frisco", address.getCity());
		assertEquals("Texas", address.getState());
		assertEquals("75034", address.getZipCode());
		
	}
	
	@Test
	public void testDeleteAddress() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("testuser@gmail.com");
		user.setPassword("test");
		
		Address mockAddress = new Address();
		mockAddress.setId(1);
		mockAddress.setAddress1("123 Test Address");
		mockAddress.setAddress2(null);
		mockAddress.setCity("Frisco");
		mockAddress.setState("Texas");
		mockAddress.setZipCode("75034");
		mockAddress.setUser(user);
		
		Mockito.when(addressService.deleteAddress(1)).thenReturn("Success");
		
		ResponseEntity<String> response = addressController.deleteAddress(1);
		
		String str = response.getBody();
		
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCodeValue());
		
		assertEquals("Success", str);
		
	}

}
