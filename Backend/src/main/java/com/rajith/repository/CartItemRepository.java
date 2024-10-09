package com.rajith.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rajith.model.Cart;
import com.rajith.model.CartItem;
import com.rajith.model.Product;

public interface CartItemRepository extends JpaRepository<CartItem,Long>{
	
	@Query("SELECT ci FROM CartItem ci WHERE ci.cart=:cart AND ci.product=:product AND ci.size=:size AND ci.userId=:userId")
	public CartItem isCartItemExist(@Param("cart") Cart cart,@Param("product") Product product,@Param("size") String size,@Param("userId") Long userId);

}
