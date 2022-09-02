package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.entity.Role;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.AddEntityException;

public interface RoleService {
	
	Role addRole(Role role) throws AddEntityException;

	Role getRoleById(Integer roleId);

	void updateRole(Role role);

	void deleteRole(Integer roleId);
	
	List<Role> getAllRolesByUserId(Integer userId);

	void assignUserToRole(Integer userid, Integer roleid);

	User getUserById(Integer userId);

}
