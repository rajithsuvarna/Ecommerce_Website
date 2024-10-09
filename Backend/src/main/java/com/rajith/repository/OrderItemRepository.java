package com.rajith.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajith.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}

