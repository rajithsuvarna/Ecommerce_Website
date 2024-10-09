package com.rajith.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	private String description;
	
	private int price;
	
	@Column(name="discounted_price")
	private int discountedPrice;
	
	@Column(name="discount_percent")
	private int discountPercent;
	
	private int quantity;
	
	private String brand;
	
	private String color;
	
	@Embedded
	@ElementCollection
	@Column(name="sizes")
	private Set<Size> sizes=new HashSet<>();
	
	@Column(name="image_url")
	private String imageUrl;
	
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,orphanRemoval=true)
	@JsonIgnore
	private List<Rating> ratings=new ArrayList<>();
	
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,orphanRemoval=true)
	@JsonIgnore
	private List<Review> reviews=new ArrayList<>();
	
	@Column(name="num_ratings")
	private int numRatings;
	
	@ManyToOne()
	@JoinColumn(name="category_id")
	private Category category;
	
	
	private LocalDateTime createdAt;
	
	@Override
	public int hashCode() {
		return Objects.hash(brand, category, color, description, discountPercent, discountedPrice, id, imageUrl,
				numRatings, price, quantity, ratings, reviews, sizes, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(category, other.category)
				&& Objects.equals(color, other.color) && Objects.equals(description, other.description)
				&& discountPercent == other.discountPercent && discountedPrice == other.discountedPrice
				&& Objects.equals(id, other.id) && Objects.equals(imageUrl, other.imageUrl)
				&& numRatings == other.numRatings && price == other.price && quantity == other.quantity
				&& Objects.equals(ratings, other.ratings) && Objects.equals(reviews, other.reviews)
				&& Objects.equals(sizes, other.sizes) && Objects.equals(title, other.title);
	}

}
