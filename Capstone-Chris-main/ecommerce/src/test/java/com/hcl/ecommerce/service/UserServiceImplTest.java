package com.hcl.ecommerce.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.ShippingAddress;
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

		userServiceImpl.addUser(mockUser);

		verify(userRepository).save(mockUser);

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

		userServiceImpl.getUserById(1);

		verify(userRepository).findById(1);

	}

	@Test
	public void testUpdateUser() throws Exception {

		User mockUser = new User(1, "larry", "miller", "larry@email.com");

		when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));

		when(userRepository.save(any(User.class))).thenReturn(mockUser);

		userServiceImpl.updateUser(mockUser);

		verify(userRepository).save(mockUser);

	}

	@Test
	public void testDeleteUser() throws Exception {

		userServiceImpl.deleteUser(1);

		verify(userRepository).deleteById(1);

	}
	
    @Test
    public void testGetAllUsers() {
    	
    	User mockUser = new User(1, "larry", "miller", "larry@email.com");
    	
        when(userRepository.getAllUsers()).thenReturn(Arrays.asList(mockUser));
        
        assertThat(userServiceImpl.getAllUsers().get(0), is(mockUser));
        
    }
    
	@Test
	public void testGetUserByEmail() {

		User mockUser = new User("larry", "miller", "larry@email.com");

		when(userRepository.findByEmail("larry@email.com")).thenReturn(mockUser);

		assertThat(userServiceImpl.getUserByEmail("larry@email.com"), is(mockUser));
		
	}
	
	@Test
	public void testGetOrdersByUserId() {

		User user = new User(1, "larry", "miller", "larry@email.com");
		
		Order order = new Order(1, LocalDate.now(), new BigDecimal(999.0), "In progress", user,
				new ShippingAddress(1, "123 Test Address", null, "Frisco", "Texas", "75034"), null);
		
		user.setOrders(Arrays.asList(order));

		when(userRepository.findById(1)).thenReturn(Optional.of(user));

		assertThat(userServiceImpl.getOrdersByUserId(1).get(0), is(order));
		
	}

}
