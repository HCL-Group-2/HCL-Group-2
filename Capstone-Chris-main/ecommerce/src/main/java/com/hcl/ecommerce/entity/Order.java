package com.hcl.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hcl.ecommerce.dto.OrderDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {
	
	public Order(OrderDto dto) {
		id = dto.getId();
		orderDate = dto.getOrderDate();
		orderTotal = dto.getOrderTotal();
		orderStatus = dto.getOrderStatus();
		user = new User(dto.getUserDto());
		shippingAddress = new ShippingAddress(dto.getShipDto());
		orderItems = new ArrayList<>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate orderDate;
	
	private BigDecimal orderTotal;
	
	private String orderStatus;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private ShippingAddress shippingAddress;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<OrderItem> orderItems = new ArrayList<>();

	public OrderDto toDto() {
		return new OrderDto(id, orderDate, orderTotal, orderStatus, user.toDto(), shippingAddress.toDto());
	}

}
