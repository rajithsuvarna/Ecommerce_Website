package com.rajith.service;

import com.rajith.exception.CartItemException;
import com.rajith.exception.UserException;
import com.rajith.model.Cart;
import com.rajith.model.CartItem;
import com.rajith.model.Product;

public interface CartItemService {
	
	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId,Long id,CartItem cartItem) throws CartItemException,UserException;
	
	public CartItem isCartItemExist(Cart cart,Product product,String size,Long userId);
	
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException,UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;

}
