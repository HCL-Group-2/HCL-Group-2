package com.hcl.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	
	@JsonProperty("user")
	private UserDto userDto;
	
	@JsonProperty("shippingAddress")
	private ShipDto shipDto;
	
	@JsonProperty("orderItems")
	private List<OrderItemDto> orderItemsDto;

}
