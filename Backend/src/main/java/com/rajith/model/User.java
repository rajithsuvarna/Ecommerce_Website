package com.rajith.model;

import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"address", "ratings", "reviews"})
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String email;
	 
	private String role;
	
	private String mobile;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval = true)
	private List<Address> address=new ArrayList<>();
	
	@Embedded
	@ElementCollection
	@CollectionTable(name="payment_information",joinColumns=@JoinColumn(name="user_id"))
	private List<PaymentInformation> paymentInformation=new ArrayList<>();
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore
	private List<Rating> ratings=new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval = true)
	private List<Review> reviews=new ArrayList<>();
	
	private LocalDateTime createdAt;
	
	

}
