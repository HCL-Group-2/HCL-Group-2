package com.hcl.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "cart_items")
public class CartItem {
	
//	@EmbeddedId
//	private CartItemId cartItemId;
//	
//	@ManyToOne
//	@JoinColumn(name = "user_id", insertable = false, updatable = false)
//	@JsonIgnore
//	User user;
//	
//	@ManyToOne
//	@JoinColumn(name = "product_id", insertable = false, updatable = false)
//	@JsonIgnore
//	Product product;
//	
//	private int quantity;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cart_item_id")
	private Integer id;
	
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

}
