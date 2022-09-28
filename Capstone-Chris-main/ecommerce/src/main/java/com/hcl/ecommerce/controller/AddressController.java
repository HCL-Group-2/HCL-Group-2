package com.hcl.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.dto.AddressDto;
import com.hcl.ecommerce.entity.Address;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.service.AddressService;



@RestController
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@PostMapping("/address")
	public ResponseEntity<AddressDto> addAddress(@RequestBody AddressDto addressDto) {
		Address address = new Address(addressDto);
		
		try {
			address = addressService.addAddress(address);
		} catch (AddEntityException e) {
			return new ResponseEntity<AddressDto>((AddressDto)null, HttpStatus.CONFLICT);
		}
		AddressDto responseDto = address.toDto();
		
		return new ResponseEntity<AddressDto>(responseDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/address/{id}")
	public ResponseEntity<AddressDto> getAddressById(@PathVariable("id") Integer id) {
		Address address = addressService.getAddressById(id);
		return new ResponseEntity<AddressDto>(address.toDto(), HttpStatus.OK);
	}
	
	@PutMapping("/address")
	public ResponseEntity<AddressDto> updateAddress(@RequestBody Address address) {
		address = addressService.updateAddress(address);
		return new ResponseEntity<AddressDto>(address.toDto(), HttpStatus.OK);
	}
	
	@DeleteMapping("/address/{id}")
	public ResponseEntity<Void> deleteAddress(@PathVariable("id") Integer id) {
		addressService.deleteAddress(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/addresses/{userid}")
	public ResponseEntity<List<AddressDto>> getAllAddressesByUserId(@PathVariable("userid") Integer userid) {
		List<Address> tempList = addressService.getAllAddressesByUserId(userid);
		List<AddressDto> list = new ArrayList();
		for(Address a : tempList) {
			list.add(a.toDto());
		}
		return new ResponseEntity<List<AddressDto>>(list, HttpStatus.OK);
	}
	
	
}
