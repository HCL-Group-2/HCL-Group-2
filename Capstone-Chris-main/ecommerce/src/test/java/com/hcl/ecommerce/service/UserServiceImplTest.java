package com.hcl.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.UserRepository;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {
	
	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;
	
//	@Test
//	public void testGetUser() {
//		User user = new User();
//		user.setUserId(1L);
//		user.setFirstName("fname1");
//		
//		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//		
//		User dbUser = userServiceImpl.getUser(1L);
//		assertNotNull(dbUser);
//		assertEquals("fname1", dbUser.getFirstName());
//	}

}
