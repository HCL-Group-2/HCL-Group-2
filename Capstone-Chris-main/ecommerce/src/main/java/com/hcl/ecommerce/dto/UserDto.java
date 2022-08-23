package com.hcl.ecommerce.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	@NotNull/*(message = "firstName should not be null")*/
	/*@Size(min = 3, max = 16)*/
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@Email
	private String email;
	
	@NotNull
	private String password;

}
