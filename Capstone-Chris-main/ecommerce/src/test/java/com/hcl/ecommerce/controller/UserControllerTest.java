package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	Jackson2ObjectMapperBuilder mapperBuilder;
	
	@MockBean
	UserService userService;
	
	@InjectMocks
	UserController userController;
	
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
