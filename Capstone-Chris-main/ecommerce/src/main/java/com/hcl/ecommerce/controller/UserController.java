package com.hcl.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.hcl.ecommerce.dto.OrderDto;
import com.hcl.ecommerce.dto.UserDto;


@CrossOrigin(origins="https://ostrichmart.azurewebsites.net/")

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	
	@PostMapping("/user")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
		User user = null;
		try {
			user = userService.addUser(new User(userDto));
		} catch (AddEntityException e) {
			return new ResponseEntity<>((UserDto) null, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(user.toDto(), HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id) {
		User user = userService.getUserById(id);
		return new ResponseEntity<>(user.toDto(), HttpStatus.OK);
	}
	
	@PutMapping("/user")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
		User user = userService.updateUser(new User(userDto));
		return new ResponseEntity<>(user.toDto(), HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<User> list = userService.getAllUsers();
		List<UserDto> dtoList = new ArrayList<>();
		for(User u : list) {
			dtoList.add(u.toDto());
		}
		return new ResponseEntity<>(dtoList, HttpStatus.OK);
	}
	
	@GetMapping("/byEmail")
	public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
		return new ResponseEntity<>(userService.getUserByEmail(email).toDto(), HttpStatus.OK);
	}
	

	@GetMapping("/user/{userid}/orders")    
	public ResponseEntity<List<OrderDto>> getOrdersById(@PathVariable("userid") Integer userId){
		List<Order> list = userService.getOrdersByUserId(userId);
		List<OrderDto> dtoList = new ArrayList<>();
		for(Order o : list) {
			dtoList.add(o.toDto());
		}
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

}
