package com.hcl.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.entity.Address;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;

	@Override
	public synchronized Address addAddress(Address address) throws AddEntityException {
		// Check if address already exist for user
		return addressRepository.save(address);
	}

	@Override
	public Address getAddressById(Integer addressId) {
		Optional<Address> address = addressRepository.findById(addressId);
		if (address.isPresent())
			return address.get();
		return null;
	}

	@Override
	public Address updateAddress(Address address) {
		Address addr = getAddressById(address.getId());
		addr.setAddress1(address.getAddress1());
		addr.setAddress2(address.getAddress2());
		addr.setCity(address.getCity());
		addr.setState(address.getState());
		addr.setZipCode(address.getZipCode());
		return addressRepository.save(addr);
	}

	@Override
	public void deleteAddress(Integer addressId) {
		addressRepository.deleteById(addressId);
	}
	
	@Override
	public List<Address> getAllAddressesByUserId(Integer userId) {
		return addressRepository.getAllAddresses(userId);
	}

}
