package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hcl.ecommerce.dto.UserDto;
import com.hcl.ecommerce.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	UserController userController;
	
	@Test
	public void testAddUser() throws Exception {
		//UserController userController = new UserController();
		
		UserDto testDto = new UserDto();
		testDto.setFirstName("fname1");
		testDto.setLastName("lname1");
		testDto.setEmail("email1@email.com");
		testDto.setPassword("pword1");
		
		ResponseEntity<User> response = userController.addUser(testDto);
		
		User testUser = response.getBody();
		assertEquals(testUser.getFirstName(), "fname1");
		assertEquals(testUser.getLastName(), "lname1");
		assertEquals(testUser.getEmail(), "email1@email.com");
		assertEquals(testUser.getPassword(), "pword1");
		
	}
	
	@Test
	public void testGetUserById() throws Exception {
		testAddUser();
		
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
		testAddUser();
		
		String mockUserJson = 
				"{\"id\":1,\"firstName\":\"Test Updated\",\"lastName\":\"User\",\"email\":\"testuser@gmail.com\",\"password\":\"test\"}";
		
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
		testAddUser();
		
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
