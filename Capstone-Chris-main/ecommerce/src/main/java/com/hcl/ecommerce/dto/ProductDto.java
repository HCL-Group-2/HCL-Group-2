package com.hcl.ecommerce.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

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
	private Integer price;
	
	@NotNull
	private String image;
	
	@NotNull
	private String category;
	
	@NotNull
	@Size(min = 0)
	private String inventory;
	
}
