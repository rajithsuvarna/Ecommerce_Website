package com.rajith.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajith.exception.ProductException;
import com.rajith.exception.UserException;
import com.rajith.model.Rating;
import com.rajith.model.Review;
import com.rajith.model.User;
import com.rajith.request.ReviewRequest;
import com.rajith.service.ReviewService;
import com.rajith.service.UserService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req,@RequestHeader("Authorization") String jwt) throws UserException,ProductException{
		
		User user=userService.findUserProfileByJWT(jwt);
		
		Review review=reviewService.createReview(req, user);
		
		return new ResponseEntity<>(review,HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>> getProductReviews(@PathVariable Long productId)throws UserException,ProductException{
		List<Review> reviews=reviewService.getAllReview(productId);
		return new ResponseEntity<>(reviews,HttpStatus.ACCEPTED);
	}

}
