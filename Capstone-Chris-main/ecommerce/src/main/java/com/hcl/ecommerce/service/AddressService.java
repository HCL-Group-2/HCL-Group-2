package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.entity.Address;
import com.hcl.ecommerce.exception.AddEntityException;

public interface AddressService {

	Address addAddress(Address address) throws AddEntityException;

	Address getAddressById(Integer addressId);

	void updateAddress(Address address);

	void deleteAddress(Integer addressId);

	List<Address> getAllAddressesByUserId(Integer userId);

}
