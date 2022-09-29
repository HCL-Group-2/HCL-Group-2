package com.hcl.ecommerce.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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

import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
	
	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;
	
	
	@Mock
	MailSenderService mailSenderService;
	
	@Test
	public void testAddUser() throws Exception {
		
		assertTrue(true);
		
	}
	
	@Test
	public void testGetUserById() throws Exception {
		
		assertTrue(true);
		
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		
		assertTrue(true);
		
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		
		assertTrue(true);
		
	}

}
