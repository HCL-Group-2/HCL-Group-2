package com.hcl.ecommerce.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.dto.UserDto;
import com.hcl.ecommerce.dto.UserLoginDto;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private MailSenderService mailSenderService;
	
	@Override
	public boolean login(UserLoginDto userLoginDto) {
		if (userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword()) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public synchronized User addUser(User user) throws AddEntityException {
		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new AddEntityException("A User with the Email: " + user.getEmail() + " already exists in the database");
		}
		userRepository.save(user);
//		mailSenderService.sendEmail(user.getEmail());
//		try {
//			mailSenderService.sendEmailWithAttachment(user.getEmail());
//		} catch (MessagingException e) {
//		} catch (IOException e) {
//		}
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
		return userRepository.save(usr);
	}

	@Override
	public void deleteUser(Integer userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	

	@Override
	public List<Order> getOrdersByUserId(Integer userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		
		if(userOptional.isPresent()) {
			return userOptional.get().getOrders();
		}
		else {
			return (List<Order>) null;
		}

	}

}
