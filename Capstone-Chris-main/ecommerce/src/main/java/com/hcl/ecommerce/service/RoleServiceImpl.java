package com.hcl.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.entity.Role;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.RoleRepository;
import com.hcl.ecommerce.repository.UserRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public synchronized boolean addRole(Role role) {
		if (roleRepository.findByName(role.getName()) != null) {
			return false;
		} else {
			roleRepository.save(role);
			return true;
		}
	}

	@Override
	public Role getRoleById(Integer roleId) {
		Optional<Role> user = roleRepository.findById(roleId);
		if (user.isPresent())
			return user.get();
		return null;
	}

	@Override
	public void updateRole(Role role) {
		Role r = getRoleById(role.getId());
		r.setName(role.getName());
		roleRepository.save(r);
	}

	@Override
	public void deleteRole(Integer roleId) {
		roleRepository.deleteById(roleId);
	}

	@Override
	public void addUser(Integer userId, Integer roleId) {
		Role role = getRoleById(roleId);
		User user = getUserById(userId);
		role.addUser(user);
		roleRepository.save(role);
	}
	
	@Override
	public User getUserById(Integer userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent())
			return user.get();
		return null;
	}

}
