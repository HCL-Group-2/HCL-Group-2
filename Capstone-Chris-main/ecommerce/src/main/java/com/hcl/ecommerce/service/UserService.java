package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.dto.UserDto;
import com.hcl.ecommerce.dto.UserLoginDto;
import com.hcl.ecommerce.entity.Role;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.AddEntityException;

public interface UserService {
	
	boolean login(UserLoginDto userLoginDto);
	
	User addUser(UserDto userDto) throws AddEntityException;

	User getUserById(Integer userId);

	void updateUser(User user);

	void deleteUser(Integer userId);

	List<User> getAllUsers();
	
	List<User> getAllUsersByRoleId(Integer roleId);

	void assignRoleToUser(Integer roleId, Integer userId);
	
	boolean addRole(Role role);

	Role getRoleById(Integer roleId);

	void deleteAllUsers();

//	User loginUser(String username, String password);

//	User registerUser(UserDto userDto);

//	User getUser(Long id);

//	User updateUser(Long userId, String firstName);
//
//	void deleteUser(Long userId);
//
//	List<User> getAllUsers();

}
