package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.entity.Role;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.dto.UserLoginDto;
import com.hcl.ecommerce.exception.AddEntityException;

public interface UserService {
	
	User addUser(User user) throws AddEntityException;

	User getUserById(Integer userId);

	User updateUser(User user);

	String deleteUser(Integer userId);

	List<User> getAllUsers();
	
	List<User> getAllUsersByRoleId(Integer roleId);

	void assignRoleToUser(Integer roleId, Integer userId);

	Role getRoleById(Integer roleId);
	
	User getUserByEmail(String email);
	
	boolean login(UserLoginDto userLoginDto);

//	User loginUser(String username, String password);

//	User registerUser(UserDto userDto);

//	User getUser(Long id);

//	User updateUser(Long userId, String firstName);
//
//	void deleteUser(Long userId);
//
//	List<User> getAllUsers();

}
