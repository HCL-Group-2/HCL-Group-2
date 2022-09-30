package com.hcl.ecommerce.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hcl.ecommerce.dto.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "products")
public class Product {
	
	public Product(ProductDto dto) {
		id = dto.getId();
		name = dto.getName();
		description = dto.getDescription();
		price = dto.getPrice();
		image = dto.getImage();
		category = dto.getCategory();
		inventory = dto.getInventory();
	}
	
	public Product(String name, String description, BigDecimal price, String image, String category, int inventory) {
		this(null, name, description, price, image, category, inventory, null, null);
	}

	public Product(Integer id, String name, String description, BigDecimal price, String image, String category,
			int inventory) {
		this(id, name, description, price, image, category, inventory, null, null);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="prod_id")
	private Integer id;
	
	@Column(name = "prod_name", nullable = false)
	private String name;
	
	@Column(name = "prod_desc", nullable = false)
	private String description;
	
	@Column(name = "prod_price", nullable = false)
	private BigDecimal price;
	
	@Column(name = "prod_image", nullable = false)
	private String image;
	
	@Column(name = "prod_category", nullable = false)
	private String category;
	
	@Column(name = "prod_inventory", nullable = false)
	private int inventory;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CartItem> cartItems = new ArrayList<>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<OrderItem> orderItems = new ArrayList<>();
	
	public ProductDto toDto() {
		return new ProductDto(id, name, description, price, image, category, inventory);
	}
	
}
