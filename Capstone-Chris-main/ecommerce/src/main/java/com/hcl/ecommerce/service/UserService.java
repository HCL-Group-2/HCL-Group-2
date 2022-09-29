package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.AddEntityException;

public interface UserService {
	
	User addUser(User user) throws AddEntityException;

	User getUserById(Integer userId);

	User updateUser(User user);

	void deleteUser(Integer userId);

	List<User> getAllUsers();

	User getUserByEmail(String email);

	List<Order> getOrdersByUserId(Integer userId);


}
