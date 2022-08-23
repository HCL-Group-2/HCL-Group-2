package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.dto.UserLoginDto;
import com.hcl.ecommerce.entity.Role;
import com.hcl.ecommerce.entity.User;

public interface UserService {
	
	boolean login(UserLoginDto userLoginDto);

	boolean addUser(User user);

	User getUserById(Integer userId);

	void updateUser(User user);

	void deleteUser(Integer userId);

	List<User> getAllUsers();

	void addRole(Integer roleId, Integer userId);

	Role getRoleById(Integer roleId);

//	User loginUser(String username, String password);

//	User registerUser(UserDto userDto);

//	User getUser(Long id);

//	User updateUser(Long userId, String firstName);
//
//	void deleteUser(Long userId);
//
//	List<User> getAllUsers();

}
