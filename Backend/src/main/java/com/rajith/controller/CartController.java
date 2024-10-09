package com.rajith.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajith.exception.ProductException;
import com.rajith.exception.UserException;
import com.rajith.model.Cart;
import com.rajith.model.CartItem;
import com.rajith.model.User;
import com.rajith.request.AddItemRequest;
import com.rajith.response.ApiResponse;
import com.rajith.service.CartService;
import com.rajith.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException{
		
		User user=userService.findUserProfileByJWT(jwt);
		Cart cart=cartService.findUserCart(user.getId());
		
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}
	
	@PutMapping("/add")
	public ResponseEntity<CartItem> addItemToCart(@RequestBody AddItemRequest req,@RequestHeader("Authorization")String jwt) throws UserException,ProductException{
		User user=userService.findUserProfileByJWT(jwt);
		CartItem item = cartService.addCartItem(user.getId(), req);
		
		
		ApiResponse res=new ApiResponse();
		res.setMessage("Item added to the cart");
		res.setStatus(true);
		return new ResponseEntity<>(item,HttpStatus.OK);
		
	}
	

}
