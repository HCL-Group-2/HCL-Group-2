package com.hcl.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto {
	
	private Integer id;
	
	private LocalDate orderDate;
	
	private BigDecimal orderTotal;
	
	private String orderStatus;
	
	private UserDto userDto;
	
	private ShipDto shipDto;
	
	private List<OrderItemDto> orderItemsDto;

}
