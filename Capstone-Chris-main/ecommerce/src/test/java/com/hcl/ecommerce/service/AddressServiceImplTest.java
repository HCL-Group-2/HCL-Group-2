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

import com.hcl.ecommerce.entity.Address;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.AddressRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceImplTest {
	
	@InjectMocks
	AddressServiceImpl addressServiceImpl;

	@Mock
	AddressRepository addressRepository;
	
	@Test
	public void testAddUser() throws Exception {
		
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
		
		Mockito.when(addressRepository.save(any(Address.class))).thenReturn(mockAddress);
		
		Address address = addressServiceImpl.addAddress(mockAddress);
		
		assertNotNull(address);
		
		assertEquals("123 Test Address", address.getAddress1());
		assertEquals(null, address.getAddress2());
		assertEquals("Frisco", address.getCity());
		assertEquals("Texas", address.getState());
		assertEquals("75034", address.getZipCode());
		
	}
	
	@Test
	public void testGetUserById() throws Exception {
		
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
		
		Mockito.when(addressRepository.findById(1)).thenReturn(Optional.of(mockAddress));
		
		Address address = addressServiceImpl.getAddressById(1);
		
		assertNotNull(address);
		
		assertEquals("123 Test Address", address.getAddress1());
		assertEquals(null, address.getAddress2());
		assertEquals("Frisco", address.getCity());
		assertEquals("Texas", address.getState());
		assertEquals("75034", address.getZipCode());
		
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		
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
		
		Mockito.when(addressRepository.findById(1)).thenReturn(Optional.of(mockAddress));
		
		Mockito.when(addressRepository.save(any(Address.class))).thenReturn(mockAddress);
		
		Address address = addressServiceImpl.updateAddress(mockAddress);
		
		assertNotNull(address);
		
		assertEquals("123 Test Address", address.getAddress1());
		assertEquals(null, address.getAddress2());
		assertEquals("Frisco", address.getCity());
		assertEquals("Texas", address.getState());
		assertEquals("75034", address.getZipCode());
		
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		
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
		
		addressServiceImpl.deleteAddress(1);
		
		Mockito.verify(addressRepository, times(1)).deleteById(1);
		
	}

}
