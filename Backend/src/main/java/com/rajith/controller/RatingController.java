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
import com.rajith.model.User;
import com.rajith.request.RatingRequest;
import com.rajith.service.RatingService;
import com.rajith.service.UserService;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/create")
	public ResponseEntity<Rating> createRating(@RequestBody RatingRequest req,@RequestHeader("Authorization") String jwt)throws UserException,ProductException{
		User user=userService.findUserProfileByJWT(jwt);
		Rating rating=ratingService.createRating(req, user);
		return new ResponseEntity<Rating>(rating,HttpStatus.CREATED);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Rating>> getProductsRating(@PathVariable Long productId,@RequestHeader("Authorization") String jwt)throws UserException,ProductException{
		User user=userService.findUserProfileByJWT(jwt);
		
		List<Rating> ratings=ratingService.getProductsRating(productId);
		
		return new ResponseEntity<>(ratings,HttpStatus.CREATED);
	}
}
