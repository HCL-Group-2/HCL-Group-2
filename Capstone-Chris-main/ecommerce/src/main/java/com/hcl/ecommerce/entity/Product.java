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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(value = { "cartItems", "orderItems" }, allowSetters = true)
public class Product {
	
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
	private List<CartItem> cartItems = new ArrayList<>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	

}
