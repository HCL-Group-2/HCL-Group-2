package com.hcl.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@JsonIgnoreProperties(value = { "user" }, allowSetters = true)
public class CreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String creditCardNumber;
	
	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String expirationDate;

	@Column(nullable = false)
	private String verificationCode; // Should not store this value

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

}
