package com.hcl.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByName(String name);

	@Query("select u.roles from User u where u.id = :userId")
	List<Role> getAllRolesByUserId(Integer userId);
	
}
