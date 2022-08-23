package com.hcl.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
