package com.hcl.ecommerce.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
	
	private Integer id;
	
	private String name;
	
	private String description;
	
	private BigDecimal price;
	
	private String image;
	
	private String category;
	
	private int inventory;
	
}
