package com.hcl.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class CreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Column(name = "cc_number", nullable = false, unique = true)
	private String creditCardNumber;
	
	@Column(name = "exp_date", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String expirationDate;

	@Column(nullable = false)
	private String securityCode;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

}
