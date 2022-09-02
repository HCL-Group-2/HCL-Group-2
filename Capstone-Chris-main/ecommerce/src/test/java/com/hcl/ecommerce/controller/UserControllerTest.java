package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@InjectMocks
	UserController userController;
	
	@Mock
	UserService userService;
	
	@Test
	public void testAddUser() throws Exception {
		
		User user = new User();
		user.setFirstName("Jane");
		user.setLastName("Doe");
		user.setEmail("janedoe@gmail.com");
		user.setPassword("jane");
		
		Mockito.when(userService.addUser(user)).thenReturn(user);
		
		ResponseEntity<User> usr = userController.addUser(user);
		
		assertEquals(HttpStatus.CREATED.value(), usr.getStatusCodeValue());
		
		assertEquals(user, usr.getBody());
		
	}
	
	@Test
	public void testGetUserById() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Jane");
		user.setLastName("Doe");
		user.setEmail("janedoe@gmail.com");
		user.setPassword("jane");
		
		Mockito.when(userService.getUserById(1)).thenReturn(user);
		
		ResponseEntity<User> usr = userController.getUserById(1);
		
		assertEquals(HttpStatus.OK.value(), usr.getStatusCodeValue());
		
		assertEquals(user, usr.getBody());
		
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Jane");
		user.setLastName("Doe");
		user.setEmail("janedoe@gmail.com");
		user.setPassword("jane");
		
		Mockito.when(userService.updateUser(user)).thenReturn(user);
		
		ResponseEntity<User> usr = userController.updateUser(user);
		
		assertEquals(HttpStatus.OK.value(), usr.getStatusCodeValue());
		
		assertEquals(user, usr.getBody());
		
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Jane");
		user.setLastName("Doe");
		user.setEmail("janedoe@gmail.com");
		user.setPassword("jane");
		
		userController.deleteUser(1);
		
		Mockito.verify(userService, times(1)).deleteUser(1);
		
	}

}
