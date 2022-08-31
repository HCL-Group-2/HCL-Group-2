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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testAddUser() throws Exception {
		String mockUserJson = 
				"{\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"testuser@gmail.com\",\"password\":\"test\"}";
		
		//Create a post request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/user/")
				.accept(MediaType.APPLICATION_JSON).content(mockUserJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		//Assert that the return status is CREATED
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		//Get the location from response header and assert that it contains the URI of the created resource
		assertEquals("http://localhost/user/1",
				response.getHeader(HttpHeaders.LOCATION));
		
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
