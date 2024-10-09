package com.rajith.service;

import java.util.*;

import com.rajith.exception.ProductException;
import com.rajith.model.Rating;
import com.rajith.model.User;
import com.rajith.request.RatingRequest;

public interface RatingService {
	
	public Rating createRating(RatingRequest req,User user) throws ProductException;
	public List<Rating> getProductsRating(Long productId);

}
