package com.rajith.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rajith.model.Review;

public interface ReviewRepository  extends JpaRepository<Review,Long>{

	@Query("SELECT r FROM Review r WHERE r.product.id=:productId")
	public List<Review> getAllProductsReview(@Param("productId") Long productId);
}
