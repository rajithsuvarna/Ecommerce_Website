package com.rajith.service;

import java.util.*;

import com.rajith.exception.ProductException;
import com.rajith.model.Review;
import com.rajith.model.User;
import com.rajith.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req,User user) throws ProductException;
	public List<Review> getAllReview(Long productId);
}
