package com.seiti.spring.orderservice.service;

import com.seiti.spring.orderservice.dto.OrderDTO;
import com.seiti.spring.orderservice.entity.Order;
import com.seiti.spring.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final StreamBridge streamBridge;
    public void placeOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItems(order.getOrderLineItems());
        orderRepository.save(order);

        log.info("Semdomg prder details to notification service.");
        streamBridge.send("notificationEventSupplier-out-0", order.getId());
    }
}
