package com.hcl.ecommerce.controller;

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

import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.service.UserService;
import com.hcl.ecommerce.dto.UserLoginDto;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class UserController {

	@Autowired
	UserService userService;
	
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
	public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
		String result = "";
		try {
			userService.deleteUser(id);
			result = "Success";
		} catch (Exception e) {
			result = "Failed";
		}
		return new ResponseEntity<String>(result, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list = userService.getAllUsers();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/users/{roleid}")
	public ResponseEntity<List<User>> getAllUsersByRoleId(@PathVariable("roleid") Integer roleid) {
		List<User> list = userService.getAllUsersByRoleId(roleid);
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@PutMapping("/user/{roleid}/{userid}")
	public ResponseEntity<Void> assignRoleToUser(@PathVariable("roleid") Integer roleid, @PathVariable("userid") Integer userid) {
		userService.assignRoleToUser(roleid, userid);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/byEmail")
	public User getUserByEmail(@RequestParam String email) {
		return userService.getUserByEmail(email);
	}

//	@PostMapping("/register")
//	public User registerUser(@RequestBody UserDto userDto) {
//		return userService.registerUser(userDto);
//	}
	
//	@GetMapping("/login")
//	public User loginUser(@RequestParam String email, @RequestParam String password) {
//		return userService.loginUser(email, password);
//	}
	
//	@GetMapping("/getUser/{userId}")
//	public User getUser(@PathVariable Long userId) {
//		log.info("inside of getUser " + userId);
//		return userService.getUser(userId);
//	}
	
//	@PostMapping("/updateUser/{userId}")
//	public User updateUser(@PathVariable Long userId, @RequestParam String firstName) {
//		return userService.updateUser(userId, firstName);
//	}
//
//	@DeleteMapping("/deleteUser/{userId}")
//	public String deleteUser(@PathVariable Long userId) {
//		try {
//			userService.deleteUser(userId);
//		} catch (Exception e) {
//			return "Failed";
//		}
//		
//		return "Success";
//	}
//
//	@GetMapping("/all")
//	public List<User> getAllUsers() {
//		return userService.getAllUsers();
//	}
	
//	
//	@GetMapping("/byEmailAndPassword")
//	public User getUserByEmail(@RequestParam String email, @RequestParam String password) {
//		return userService.getUserByEmailAndPassword(email, password);
//	}

}
