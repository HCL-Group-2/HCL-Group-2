package com.hcl.ecommerce.dto;

import javax.validation.constraints.NotNull;

import com.hcl.ecommerce.entity.Address;
import com.hcl.ecommerce.entity.CreditCard;
import com.hcl.ecommerce.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
	
	private Integer id;
	
	@NotNull
	private User user;
	
	@NotNull
	private Address address;
	
	@NotNull
	private CreditCard creditCard;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	

}
