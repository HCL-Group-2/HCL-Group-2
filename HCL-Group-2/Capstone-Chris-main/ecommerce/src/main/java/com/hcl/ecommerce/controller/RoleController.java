package com.hcl.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.entity.Role;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.service.RoleService;

@RestController
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@PostMapping("/role")
	public ResponseEntity<Role> addRole(@RequestBody Role role) {
		Role newRole = null;
		try {
			newRole = roleService.addRole(role);
		} catch(AddEntityException e) {
			return new ResponseEntity<Role>(newRole, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Role>(newRole, HttpStatus.CREATED);
	}
	
	@GetMapping("/role/{id}")
	public ResponseEntity<Role> getRoleById(@PathVariable("id") Integer id) {
		Role role = roleService.getRoleById(id);
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}
	
	@PutMapping("/role")
	public ResponseEntity<Role> updateRole(@RequestBody Role role) {
		roleService.updateRole(role);
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}
	
	@DeleteMapping("/role/{id}")
	public ResponseEntity<Void> deleteRole(@PathVariable("id") Integer id) {
		roleService.deleteRole(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/roles/{userid}")
	public ResponseEntity<List<Role>> getAllRolesByUserId(@PathVariable("userid") Integer userid) {
		List<Role> list = roleService.getAllRolesByUserId(userid);
		return new ResponseEntity<List<Role>>(list, HttpStatus.OK);
	}
	
	@PutMapping("/role/{userid}/{roleid}")
	public ResponseEntity<Void> assignUserToRole(@PathVariable("userid") Integer userid, @PathVariable("roleid") Integer roleid) {
		roleService.assignUserToRole(userid, roleid);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
