package com.hcl.ecommerce.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
	private UserDto userDto;
	
	@NotNull
	private ProductDto productDto;

	public CartItemDto(Integer id2, int quantity2, BigDecimal subtotal2, UserDto dto, ProductDto dto2) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public ProductDto getProductDto() {
		return productDto;
	}

	public void setProductDto(ProductDto productDto) {
		this.productDto = productDto;
	}
	


}
