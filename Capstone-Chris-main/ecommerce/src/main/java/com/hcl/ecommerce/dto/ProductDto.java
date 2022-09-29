package com.hcl.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
	
	private Integer id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String description;
	
	@NotNull
	@Size(min = 0)
	private BigDecimal price;
	
	@NotNull
	private String image;
	
	@NotNull
	private String category;
	
	@NotNull
	@Size(min = 0)
	private int inventory;
	
	private List<CartItemDto> cartListDto;
	
	private List<OrderItemDto> orderListDto;
	
	
}
