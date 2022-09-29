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
public class OrderItemDto {
	private Integer id;
	
	private int quantity;
	
	private BigDecimal subtotal;
	
	private OrderDto orderDto;
	
	private ProductDto productDto;
}
