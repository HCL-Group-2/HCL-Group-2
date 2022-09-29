package com.hcl.ecommerce.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItemDto {
	
	private Integer id;
	
	@NotNull
	@Size(min = 0)
	private int quantity;
	
	private BigDecimal subtotal;
	
	@NotNull
	@JsonProperty("user")
	private UserDto userDto;
	
	@NotNull
	@JsonProperty("product")
	private ProductDto productDto;
	
}
