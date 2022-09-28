package com.hcl.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hcl.ecommerce.dto.AddressDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = { "user" }, allowSetters = true)
public class Address {

	public Address(AddressDto dto) {
		id = dto.getId();
		address1 = dto.getAddress1();
		address2 =dto.getAddress2();
		city = dto.getCity();
		state = dto.getState();
		zipCode = dto.getZipCode();
		user = new User(dto.getUserDto());
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String address1;

	private String address2;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String state;

	@Column(nullable = false)
	private String zipCode;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	public AddressDto toDto() {
		AddressDto dto = new AddressDto(id, address1, address2, city, state, zipCode, user.toDto());
		
		return dto;
	}
	
}
