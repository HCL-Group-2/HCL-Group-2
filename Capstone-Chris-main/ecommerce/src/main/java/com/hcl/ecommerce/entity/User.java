package com.hcl.ecommerce.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hcl.ecommerce.dto.UserDto;

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
	private List<Address> addresses = new ArrayList<>();
	
	
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//	private List<CreditCard> creditCards = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<CartItem> cartItems = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();
	
	
	public UserDto toDto() {
		UserDto dto = new UserDto(id, firstName, lastName, email);
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

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
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
