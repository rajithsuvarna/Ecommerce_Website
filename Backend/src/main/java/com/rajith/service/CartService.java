package com.rajith.service;

import com.rajith.exception.ProductException;
import com.rajith.model.Cart;
import com.rajith.model.CartItem;
import com.rajith.model.User;
import com.rajith.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);
	
	public CartItem addCartItem(Long userId,AddItemRequest req)throws ProductException;
	
	public Cart findUserCart(Long userId);
	
	
}
