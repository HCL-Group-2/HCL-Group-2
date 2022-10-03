package com.hcl.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.hcl.ecommerce.dto.ShipDto;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShippingAddress {
	
	public ShippingAddress(ShipDto dto) {
		id = dto.getId();
		address1 = dto.getAddress1();
		address2 = dto.getAddress2();
		city = dto.getCity();
		state = dto.getState();
		zipCode = dto.getZipCode();
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

	public ShipDto toDto() {
		return new ShipDto(id, address1, address2, city, state, zipCode);
	}
}
