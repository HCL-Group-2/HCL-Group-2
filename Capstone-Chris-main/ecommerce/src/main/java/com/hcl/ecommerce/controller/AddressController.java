package com.hcl.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.entity.Address;
import com.hcl.ecommerce.service.AddressService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@PostMapping("/address")
	public ResponseEntity<Void> addAddress(@RequestBody Address address) {
		boolean flag = addressService.addAddress(address);
		if (!flag) return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("/address/{id}")
	public ResponseEntity<Address> getAddressById(@PathVariable("id") Integer id) {
		Address address = addressService.getAddressById(id);
		return new ResponseEntity<Address>(address, HttpStatus.OK);
	}
	
	@PutMapping("/address")
	public ResponseEntity<Address> updateAddress(@RequestBody Address address) {
		addressService.updateAddress(address);
		return new ResponseEntity<Address>(address, HttpStatus.OK);
	}
	
	@DeleteMapping("/address/{id}")
	public ResponseEntity<Void> deleteAddress(@PathVariable("id") Integer id) {
		addressService.deleteAddress(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
