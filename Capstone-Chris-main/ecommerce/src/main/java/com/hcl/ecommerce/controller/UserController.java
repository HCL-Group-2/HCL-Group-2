package com.hcl.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.service.UserService;
import com.hcl.ecommerce.dto.UserLoginDto;


@CrossOrigin(origins="https://ostrichmart.azurewebsites.net/")

@RestController
public class UserController {

	@Autowired
	UserService userService;
	

	@PostMapping("/login")
	public ResponseEntity<Void> login(@RequestBody UserLoginDto userLoginDto) {
		boolean flag = userService.login(userLoginDto);
		if (!flag) return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		try {
			user = userService.addUser(user);
		} catch (AddEntityException e) {
			return new ResponseEntity<User>(user, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
		User user = userService.getUserById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PutMapping("/user")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		user = userService.updateUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/users")
//	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list = userService.getAllUsers();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/byEmail")
	public User getUserByEmail(@RequestParam String email) {
		return userService.getUserByEmail(email);
	}
	

	@GetMapping("/user/{userid}/orders")    
	public ResponseEntity<List<Order>> getOrdersById(@PathVariable("userid") Integer userId){
        return new ResponseEntity<List<Order>>(userService.getOrdersByUserId(userId), HttpStatus.OK);
    }

}
