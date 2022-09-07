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
		
		String mockUserJson = 
				"{\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"testuser@gmail.com\",\"password\":\"test\"}";
		
		ObjectMapper mapper = mapperBuilder.build();
        User mockUser = mapper.readValue(mockUserJson, User.class);
		
		Mockito.when(userService.addUser(any(User.class))).thenReturn(mockUser);
		
		//Create a post request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/user/")
				.accept(MediaType.APPLICATION_JSON).content(mockUserJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		//Assert that the return status is CREATED
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
	}
	
	@Test
	public void testGetUserById() throws Exception {
		
		String mockUserJson = "{\"id\":1,\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"testuser@gmail.com\",\"password\":\"test\"}";
		
		ObjectMapper mapper = mapperBuilder.build();
        User mockUser = mapper.readValue(mockUserJson, User.class);
        
        Mockito.when(userService.getUserById(1)).thenReturn(mockUser);
		
		//Create a request
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/user/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "{\"id\":1,\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"testuser@gmail.com\",\"password\":\"test\"}";
		
		//Assert that response is what was expected
		assertEquals(expected, result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		
		String mockUserJson = 
				"{\"id\":1,\"firstName\":\"Test Updated\",\"lastName\":\"User\",\"email\":\"testuser@gmail.com\",\"password\":\"test\"}";
		
		ObjectMapper mapper = mapperBuilder.build();
        User mockUser = mapper.readValue(mockUserJson, User.class);
        
        Mockito.when(userService.updateUser(any(User.class))).thenReturn(mockUser);
		
        //Create a put request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/user/")
				.accept(MediaType.APPLICATION_JSON).content(mockUserJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "{\"id\":1,\"firstName\":\"Test Updated\",\"lastName\":\"User\",\"email\":\"testuser@gmail.com\",\"password\":\"test\"}";
		
		//Assert that response is what was expected
		assertEquals(expected, result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		
		String mockUserJson = 
				"{\"id\":1,\"firstName\":\"Test Updated\",\"lastName\":\"User\",\"email\":\"testuser@gmail.com\",\"password\":\"test\"}";
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/user/1")
				.accept(MediaType.APPLICATION_JSON).content(mockUserJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		//Assert that the return status is 204 No Content
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
		
	}

}
