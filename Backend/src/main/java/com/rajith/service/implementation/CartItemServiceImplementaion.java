package com.rajith.service.implementation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rajith.exception.CartItemException;
import com.rajith.exception.UserException;
import com.rajith.model.Cart;
import com.rajith.model.CartItem;
import com.rajith.model.Product;
import com.rajith.model.User;
import com.rajith.repository.CartItemRepository;
import com.rajith.repository.CartRepository;
import com.rajith.service.CartItemService;
import com.rajith.service.UserService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Service
@Data
@AllArgsConstructor
public class CartItemServiceImplementaion implements CartItemService {

	private CartItemRepository cartItemRepository;
	private UserService userService;
	private CartRepository cartRepository;
	
	@Override
	public CartItem createCartItem(CartItem cartItem) {
		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
		cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
		
		CartItem createdCartItem=cartItemRepository.save(cartItem);
		
		return createdCartItem;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
		CartItem item=findCartItemById(id);
		User user=userService.findUserById(item.getUserId());
		
		if(user.getId().equals(userId)) {
			
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getQuantity()*item.getProduct().getPrice());
			item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
			return cartItemRepository.save(item);
		}else {
			throw new CartItemException("You can't update  another users cart_item");
		}
		
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
		CartItem cartItem=cartItemRepository.isCartItemExist(cart, product, size, userId); 
		
		return cartItem;
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
		CartItem cartItem=findCartItemById(cartItemId);
		
		User user=userService.findUserById(cartItem.getUserId());
		
		User reqUser=userService.findUserById(userId);
		
		if(user.getId().equals(reqUser.getId())) {
			cartItemRepository.deleteById(cartItem.getId());
		}else {
			throw new UserException("You Can't Remove Another User's item");
			
		}
	}

	@Override
	public CartItem findCartItemById(Long cartItemId) throws CartItemException {
		
		Optional<CartItem> opt=cartItemRepository.findById(cartItemId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new CartItemException("CartItem not found with id:"+cartItemId);
		
		
	}

}
