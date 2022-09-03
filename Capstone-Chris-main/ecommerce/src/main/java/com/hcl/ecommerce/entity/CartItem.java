package com.hcl.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_item_id")
	private Integer id;
	
	@Column(nullable = false)
	private int quantity;
	
	private double subtotal;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Override
	public boolean equals(Object o) {
		CartItem item = null;
		if (o instanceof CartItem) {
			item = (CartItem) o;
		} else {
			return false;
		}
		
		if ((this.user.getId() == item.getUser().getId()) && (this.product.getId() == item.getProduct().getId())) {
			return true;
		} else {
			return false;
		}
		
	}

}
