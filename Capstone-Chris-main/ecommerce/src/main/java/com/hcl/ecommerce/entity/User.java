package com.hcl.ecommerce.entity;

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
import com.hcl.ecommerce.dto.UserDto;
import com.hcl.ecommerce.dto.CartItemDto;
import com.hcl.ecommerce.dto.OrderDto;

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
@Table(name = "users")
@JsonIgnoreProperties(value = { "addresses", "creditCards", "cartItems", "orders", "roles" }, allowSetters = true)
public class User {

	public User(UserDto dto) {
		id = dto.getId();
		firstName = dto.getFirstName();
		lastName = dto.getLastName();
		email = dto.getEmail();
		cartItems = new ArrayList<CartItem>();
		for(CartItemDto cid : dto.getCartItems()) {
			cartItems.add(new CartItem(cid));
		}
		orders = new ArrayList<Order>();
		for(OrderDto o : dto.getOrders()) {
			orders.add(new Order(o));
		}
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false, unique = true)
	private String email;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<CartItem> cartItems;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Order> orders;
	
	
	public UserDto toDto() {
		List<CartItemDto> dtoListCart = new ArrayList<CartItemDto>();
		for(CartItem c : cartItems) {
			dtoListCart.add(c.toDto());
		}
		List<OrderDto> dtoListOrder = new ArrayList<OrderDto>();
		for(Order o : orders) {
			dtoListOrder.add(o.toDto());
		}
		UserDto dto = new UserDto(id, firstName, lastName, email, dtoListCart, dtoListOrder);
		return dto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


//	public List<CreditCard> getCreditCards() {
//		return creditCards;
//	}

//	public void setCreditCards(List<CreditCard> creditCards) {
//		this.creditCards = creditCards;
//	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}


}
