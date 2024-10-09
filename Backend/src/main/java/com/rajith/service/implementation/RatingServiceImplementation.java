package com.rajith.service.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rajith.exception.ProductException;
import com.rajith.model.Product;
import com.rajith.model.Rating;
import com.rajith.model.User;
import com.rajith.repository.RatingRepository;
import com.rajith.request.RatingRequest;
import com.rajith.service.ProductService;
import com.rajith.service.RatingService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@AllArgsConstructor
public class RatingServiceImplementation implements RatingService {
	
	private RatingRepository ratingRepository;
	private ProductService productService;

	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {
		Product product=productService.findProductById(req.getProductId());
		
		Rating rating=new Rating();
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		
		return ratingRepository.getAllProductsRating(productId);
	}

}
