package com.seiti.spring.orderservice.repository;

import com.seiti.spring.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
