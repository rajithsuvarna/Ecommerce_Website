package com.rajith.service.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rajith.exception.ProductException;
import com.rajith.model.Address;
import com.rajith.model.PaymentInformation;
import com.rajith.model.Product;
import com.rajith.model.Rating;
import com.rajith.model.Review;
import com.rajith.model.User;
import com.rajith.repository.ProductRepository;
import com.rajith.repository.ReviewRepository;
import com.rajith.request.ReviewRequest;
import com.rajith.service.ProductService;
import com.rajith.service.ReviewService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@AllArgsConstructor
public class ReviewServiceImplementation implements ReviewService{
	
	private ReviewRepository reviewRepository;
	private ProductService productService;
	private ProductRepository productRepository;

	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		Product product=productService.findProductById(req.getProductId());
		Review review=new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
		product.getReviews().add(review);
		productRepository.save(product);
		
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {
		
		return reviewRepository.getAllProductsReview(productId);
	}

}
