package com.hcl.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ShipDto {
	private Integer id;

	private String address1;

	private String address2;

	private String city;

	private String state;

	private String zipCode;
}
