package com.hcl.ecommerce.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	private Integer id;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@Email
	private String email;
	
	@NotNull
	private String password;

}
