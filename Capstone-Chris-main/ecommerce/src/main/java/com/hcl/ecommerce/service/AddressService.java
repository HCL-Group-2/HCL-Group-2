package com.hcl.ecommerce.service;

import com.hcl.ecommerce.entity.Address;

public interface AddressService {

	boolean addAddress(Address address);

	Address getAddressById(Integer addressId);

	void updateAddress(Address address);

	void deleteAddress(Integer addressId);

}
