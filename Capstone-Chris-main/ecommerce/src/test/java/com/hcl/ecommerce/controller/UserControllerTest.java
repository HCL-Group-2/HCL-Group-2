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

import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	
	@InjectMocks
	UserController userController;
	
	@Mock
	UserService userService;
	
	@Test
	public void testAddUser() throws Exception {
		
		User mockUser = new User();
		mockUser.setId(1);
		mockUser.setFirstName("Test");
		mockUser.setLastName("User");
		mockUser.setEmail("testuser@gmail.com");
		mockUser.setPassword("test");
		
		Mockito.when(userService.addUser(any(User.class))).thenReturn(mockUser);
		
		ResponseEntity<User> response = userController.addUser(mockUser);
		
		User user = response.getBody();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());
		
		assertEquals("Test", user.getFirstName());
		assertEquals("User", user.getLastName());
		assertEquals("testuser@gmail.com", user.getEmail());
		assertEquals("test", user.getPassword());
		
	}
	
	@Test
	public void testGetUserById() throws Exception {
		
		User mockUser = new User();
		mockUser.setId(1);
		mockUser.setFirstName("Test");
		mockUser.setLastName("User");
		mockUser.setEmail("testuser@gmail.com");
		mockUser.setPassword("test");
		
		Mockito.when(userService.getUserById(1)).thenReturn(mockUser);
		
		ResponseEntity<User> response = userController.getUserById(1);
		
		User user = response.getBody();
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
		assertEquals("Test", user.getFirstName());
		assertEquals("User", user.getLastName());
		assertEquals("testuser@gmail.com", user.getEmail());
		assertEquals("test", user.getPassword());
		
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		
		User mockUser = new User();
		mockUser.setId(1);
		mockUser.setFirstName("Test");
		mockUser.setLastName("User");
		mockUser.setEmail("testuser@gmail.com");
		mockUser.setPassword("test");
		
		Mockito.when(userService.updateUser(any(User.class))).thenReturn(mockUser);
		
		ResponseEntity<User> response = userController.updateUser(mockUser);
		
		User user = response.getBody();
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
		assertEquals("Test", user.getFirstName());
		assertEquals("User", user.getLastName());
		assertEquals("testuser@gmail.com", user.getEmail());
		assertEquals("test", user.getPassword());
		
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		
		User mockUser = new User();
		mockUser.setId(1);
		mockUser.setFirstName("Test");
		mockUser.setLastName("User");
		mockUser.setEmail("testuser@gmail.com");
		mockUser.setPassword("test");
		
		Mockito.when(userService.deleteUser(1)).thenReturn("Success");
		
		ResponseEntity<String> response = userController.deleteUser(1);
		
		String str = response.getBody();
		
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCodeValue());
		
		assertEquals("Success", str);
		
	}

}
