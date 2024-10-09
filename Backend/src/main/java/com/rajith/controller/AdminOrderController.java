package com.rajith.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajith.exception.OrderException;
import com.rajith.model.Order;
import com.rajith.response.ApiResponse;
import com.rajith.service.OrderService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/orders")
public class AdminOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/")
	public ResponseEntity<List<Order>> getAllOrdersHandler(){
		List<Order> orders=orderService.getAllOrders();
		
		return new ResponseEntity<List<Order>>(orders,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{orderId}/confirmed")
	public ResponseEntity<Order> ConfirmedOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt) throws OrderException{
		
		Order order=orderService.confirmedOrder(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/ship")
	public ResponseEntity<Order> ShippedOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt) throws OrderException{
		
		Order order=orderService.shippedOrder(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
		
	}
	
	@PutMapping("/{orderId}/deliver")
	public ResponseEntity<Order> DeliverOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt) throws OrderException{
		
		Order order=orderService.deliveredOrder(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
		
	}
	
	@PutMapping("/{orderId}/cancel")
	public ResponseEntity<Order> CancelrOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt) throws OrderException{
		
		Order order=orderService.cancelledOrder(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{orderId}/delete")
	public ResponseEntity<ApiResponse> DeleteOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt) throws OrderException{
		
		orderService.deleteOrder(orderId);
		
		ApiResponse res=new ApiResponse();
		res.setMessage("Order deleted successfully");
		res.setStatus(true);
		
		return new ResponseEntity<>(res,HttpStatus.OK);
		
	}
	
}
