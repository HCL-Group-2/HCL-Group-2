package com.hcl.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.entity.Role;
import com.hcl.ecommerce.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);
	
	User findByEmailAndPassword(String email, String password);
	
	@Query("select u from User u order by u.lastName")
	List<User> getAllUsers();

	@Query("select r.users from Role r where r.id = :roleId")
	List<User> getUsersByRoleId(Integer roleId);
	
}
