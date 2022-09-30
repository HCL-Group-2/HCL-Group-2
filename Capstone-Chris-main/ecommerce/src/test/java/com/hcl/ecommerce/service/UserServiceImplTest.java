package com.hcl.ecommerce.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	@Test
	public void testAddUser() throws Exception {

		User mockUser = new User("larry", "miller", "larry@email.com");

		when(userRepository.findByEmail(mockUser.getEmail())).thenReturn(null);

		when(userRepository.save(any(User.class))).thenReturn(mockUser);

		User user = userServiceImpl.addUser(mockUser);

		verify(userRepository).save(mockUser);

//		assertNotNull(user);
//		
//		assertEquals("larry", user.getFirstName());
//		assertEquals("miller", user.getLastName());
//		assertEquals("larry@email.com", user.getEmail());

	}
	
	@Test(expected = AddEntityException.class)
	public void testAddUserUserAlreadyExist() throws Exception {

		User mockUser = new User("larry", "miller", "larry@email.com");

		when(userRepository.findByEmail(mockUser.getEmail())).thenReturn(mockUser);

		userServiceImpl.addUser(mockUser);

	}

	@Test
	public void testGetUserById() throws Exception {

		User mockUser = new User("larry", "miller", "larry@email.com");

		when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));

		User user = userServiceImpl.getUserById(1);

		verify(userRepository).findById(1);

//		assertNotNull(user);
//		
//		assertEquals("larry", user.getFirstName());
//		assertEquals("miller", user.getLastName());
//		assertEquals("larry@email.com", user.getEmail());

	}

	@Test
	public void testUpdateUser() throws Exception {

		User mockUser = new User(1, "larry", "miller", "larry@email.com");

		when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));

		when(userRepository.save(any(User.class))).thenReturn(mockUser);

		User user = userServiceImpl.updateUser(mockUser);

		verify(userRepository).save(mockUser);

//		assertNotNull(user);
//		
//		assertEquals("larry", user.getFirstName());
//		assertEquals("miller", user.getLastName());
//		assertEquals("larry@email.com", user.getEmail());

	}

	@Test
	public void testDeleteUser() throws Exception {

		userServiceImpl.deleteUser(1);

		verify(userRepository).deleteById(1);

	}

}
