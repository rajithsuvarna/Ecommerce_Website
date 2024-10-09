package com.rajith.service.implementation;

import org.springframework.stereotype.Service;

import com.rajith.model.OrderItem;
import com.rajith.repository.OrderItemRepository;
import com.rajith.service.OrderItemService;

@Service
public class OrderItemServiceImplementation implements OrderItemService {
	
	private OrderItemRepository orderItemRepository;
	
	public OrderItemServiceImplementation(OrderItemRepository orderItemRepository) {
		this.orderItemRepository=orderItemRepository;
	}
	
	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		
		return orderItemRepository.save(orderItem);
	}

}
