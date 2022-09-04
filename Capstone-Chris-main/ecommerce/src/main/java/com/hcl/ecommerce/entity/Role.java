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
@Table(name = "roles")
@JsonIgnoreProperties(value = { "users" }, allowSetters = true)
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer id;

	@Column(name = "role_name", nullable = false)
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY, 
			cascade =
			{
					CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.REFRESH,
					CascadeType.PERSIST
			},
			targetEntity = User.class)
	@JoinTable(name = "user_roles", 
		joinColumns = @JoinColumn(name = "role_id",
			nullable = false,
			updatable = false),
		inverseJoinColumns = @JoinColumn(name = "user_id", 
			nullable = false,
			updatable = false),
		foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
		inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
	private List<User> users = new ArrayList<>();
	
	public void addUser(User user) {
		this.users.add(user);
	}

}
