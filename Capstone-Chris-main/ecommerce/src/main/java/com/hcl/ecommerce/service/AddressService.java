package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.entity.Address;
import com.hcl.ecommerce.exception.AddEntityException;

public interface AddressService {

	Address addAddress(Address address) throws AddEntityException;

	Address getAddressById(Integer addressId);

	Address updateAddress(Address address);

	String deleteAddress(Integer addressId);

	List<Address> getAllAddressesByUserId(Integer userId);

}
