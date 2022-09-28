package com.hcl.ecommerce.dto;

import com.hcl.ecommerce.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AddressDto {

	private Integer id;
	
	private String address1;

	private String address2;
	
	private String city;
	
	private String state;
	
	private String zipCode;
	
	private UserDto userDto;
}
