package com.hcl.ecommerce.entity;

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
public class User {

	public User(UserDto dto) {
		id = dto.getId();
		firstName = dto.getFirstName();
		lastName = dto.getLastName();
		email = dto.getEmail();
	}
	
	public User(String firstName, String lastName, String email) {
		this(null, firstName, lastName, email, null, null);
	}

	public User(Integer id, String firstName, String lastName, String email) {
		this(id, firstName, lastName, email, null, null);
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
	@JsonIgnore
	private List<CartItem> cartItems;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Order> orders;
	
	public UserDto toDto() {
		return new UserDto(id, firstName, lastName, email);
	}

}
