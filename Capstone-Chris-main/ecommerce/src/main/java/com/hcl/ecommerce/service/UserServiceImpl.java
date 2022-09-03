package com.hcl.ecommerce.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.entity.Role;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.repository.RoleRepository;
import com.hcl.ecommerce.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private MailSenderService mailSenderService;
	
	@Override
	public synchronized User addUser(User user) throws AddEntityException {
		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new AddEntityException("A User with the Email: " + user.getEmail() + " already exists in the database");
		}
		if (roleRepository.findByName("Customer") == null) {
			Role role = new Role();
			role.setName("Customer");
			roleRepository.save(role);
		}
		userRepository.save(user);
		Role role = roleRepository.findByName("Customer");
		assignRoleToUser(role.getId(), user.getId());
//			mailSenderService.sendEmail(user.getEmail());
//			try {
//				mailSenderService.sendEmailWithAttachment(user.getEmail());
//			} catch (MessagingException e) {
//			} catch (IOException e) {
//			}
		return user;
	}

	@Override
	public User getUserById(Integer userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent())
			return user.get();
		return null;
	}

	@Override
	public User updateUser(User user) {
		User usr = getUserById(user.getId());
		usr.setFirstName(user.getFirstName());
		usr.setLastName(user.getLastName());
		usr.setEmail(user.getEmail());
		usr.setPassword(user.getPassword());
		return userRepository.save(usr);
	}

	@Override
	public String deleteUser(Integer userId) {
		userRepository.deleteById(userId);
		return "Success";
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}
	
	@Override
	public List<User> getAllUsersByRoleId(Integer roleId) {
		return userRepository.getUsersByRoleId(roleId);
	}

	@Override
	public void assignRoleToUser(Integer roleId, Integer userId) {
		User user = getUserById(userId);
		Role role = getRoleById(roleId);
		user.addRole(role);
		userRepository.save(user);
	}

	@Override
	public Role getRoleById(Integer roleId) {
		Optional<Role> role = roleRepository.findById(roleId);
		if (role.isPresent())
			return role.get();
		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

//	@Override
//	public User registerUser(UserDto userDto) {
//		User user = new User();
//		
//		BeanUtils.copyProperties(userDto, user);
//		
//		mailSenderService.sendEmail(user.getEmail());
//		
//		return userRepository.save(user);
//	}
//	
//	@Override
//	public User loginUser(String email, String password) {
//		return userRepository.findByEmailAndPassword(email, password);
//	}
//	
//	@Override
//	public User getUser(Long id) {
//		Optional<User> user = userRepository.findById(id);
//		
//		if (user.isPresent())
//			return user.get();
//		
//		return null;
//	}
//
//	@Override
//	public User updateUser(Long userId, String firstName) {
//		User user = getUser(userId);
//		
//		user.setFirstName(firstName);
//		
//		return userRepository.save(user);
//	}
//
//	@Override
//	public void deleteUser(Long userId) {
//		userRepository.deleteById(userId);
//	}
//
//	@Override
//	public List<User> getAllUsers() {
//		return userRepository.findAll();
//	}
//
//	@Override
//	public User getUserByEmail(String email, String password) {
//		//return userRepository.findByUsername(email);
//		return userRepository.findByEmailAndPassword(email, password);
//	}

}
