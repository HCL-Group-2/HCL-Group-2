package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.entity.Role;
import com.hcl.ecommerce.entity.User;

public interface RoleService {
	
	boolean addRole(Role role);

	Role getRoleById(Integer roleId);

	void updateRole(Role role);

	void deleteRole(Integer roleId);
	
	List<Role> getAllRolesByUserId(Integer userId);

	void assignUserToRole(Integer userid, Integer roleid);

	User getUserById(Integer userId);

}
