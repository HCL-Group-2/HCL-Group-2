package com.hcl.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hcl.ecommerce.dto.UserDto;
import com.hcl.ecommerce.dto.UserLoginDto;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.service.UserService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/login")
	public ResponseEntity<Void> login(@RequestBody UserLoginDto userLoginDto) {
		boolean flag = userService.login(userLoginDto);
		if (!flag) return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> addUser(@RequestBody UserDto userDto) {
		User user = null;
		try {
			user = userService.addUser(userDto);
		} catch (AddEntityException e) {
			return new ResponseEntity<User>(user, HttpStatus.CONFLICT);
		}
		//if (!flag) return new ResponseEntity<User>(HttpStatus.CONFLICT);
		//HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(builder.path("/user/{id}").buildAndExpand(userDto.getId()).toUri());
		
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
		User user = userService.getUserById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PutMapping("/user")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		userService.updateUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
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
	
//	@GetMapping("/byEmail")
//	public User getUserByEmail(@RequestParam String email) {
//		return userService.getUserByEmail(email);
//	}
//	
//	@GetMapping("/byEmailAndPassword")
//	public User getUserByEmail(@RequestParam String email, @RequestParam String password) {
//		return userService.getUserByEmailAndPassword(email, password);
//	}

}
