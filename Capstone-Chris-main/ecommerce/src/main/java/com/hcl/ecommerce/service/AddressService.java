package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.entity.Address;

public interface AddressService {

	boolean addAddress(Address address);

	Address getAddressById(Integer addressId);

	void updateAddress(Address address);

	void deleteAddress(Integer addressId);

	List<Address> getAllAddressesByUserId(Integer userId);

}
