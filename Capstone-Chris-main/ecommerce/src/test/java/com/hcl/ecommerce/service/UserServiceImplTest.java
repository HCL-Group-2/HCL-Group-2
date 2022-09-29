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
		
		User mockUser = new User();
		mockUser.setId(1);
		mockUser.setFirstName("Test");
		mockUser.setLastName("User");
		mockUser.setEmail("testuser@gmail.com");
		
		Mockito.when(userRepository.findByEmail(mockUser.getEmail())).thenReturn(null);
		
		Mockito.when(userRepository.save(any(User.class))).thenReturn(mockUser);
		
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));
		
		Mockito.doNothing().when(mailSenderService).sendEmail(null);
		
		Mockito.doNothing().when(mailSenderService).sendEmailWithAttachment(null);
		
		User user = userServiceImpl.addUser(mockUser);
		
		assertNotNull(user);
		
		assertEquals("Test", user.getFirstName());
		assertEquals("User", user.getLastName());
		assertEquals("testuser@gmail.com", user.getEmail());
		
	}
	
	@Test
	public void testGetUserById() throws Exception {
		
		User mockUser = new User();
		mockUser.setId(1);
		mockUser.setFirstName("Test");
		mockUser.setLastName("User");
		mockUser.setEmail("testuser@gmail.com");
		
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));
		
		User user = userServiceImpl.getUserById(1);
		
		assertNotNull(user);
		
		assertEquals("Test", user.getFirstName());
		assertEquals("User", user.getLastName());
		assertEquals("testuser@gmail.com", user.getEmail());
		
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		
		User mockUser = new User();
		mockUser.setId(1);
		mockUser.setFirstName("Test");
		mockUser.setLastName("User");
		mockUser.setEmail("testuser@gmail.com");
		
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));
		
		Mockito.when(userRepository.save(any(User.class))).thenReturn(mockUser);
		
		User user = userServiceImpl.updateUser(mockUser);
		
		assertNotNull(user);
		
		assertEquals("Test", user.getFirstName());
		assertEquals("User", user.getLastName());
		assertEquals("testuser@gmail.com", user.getEmail());
		
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		
		User mockUser = new User();
		mockUser.setId(1);
		mockUser.setFirstName("Test");
		mockUser.setLastName("User");
		mockUser.setEmail("testuser@gmail.com");
		
		userServiceImpl.deleteUser(1);
		
		Mockito.verify(userRepository, times(1)).deleteById(1);
		
	}

}
