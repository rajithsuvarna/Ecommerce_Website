package com.rajith.model;

import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	@OneToMany(mappedBy="cart",cascade=CascadeType.ALL,orphanRemoval=true)
	@Column(name="cart_items")
	private List<CartItem> cartItems=new ArrayList<>();
	
	@Column(name="total_price")
	private double totalPrice;
	
	@Column(name="total_item")
	private int totalItem;
	
	private int totalDiscountedPrice;
	
	private int discount;
}
