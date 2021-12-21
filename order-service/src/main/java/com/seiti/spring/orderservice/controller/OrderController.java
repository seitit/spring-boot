package com.seiti.spring.orderservice.controller;

import com.seiti.spring.orderservice.client.InventoryClient;
import com.seiti.spring.orderservice.dto.OrderDTO;
import com.seiti.spring.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final InventoryClient inventoryClient;
    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;


    @PostMapping
    public ResponseEntity placeOrder(@RequestBody OrderDTO orderDTO) {
        Resilience4JCircuitBreaker circuitBreaker = circuitBreakerFactory.create("inventory");

        Supplier<Boolean> booleanSupplier = () -> orderDTO.getOrderLineItemsList().stream()
                .allMatch(orderLineItems -> inventoryClient.checkStock(orderLineItems.getSkuCode()));
        boolean allProductsInStock = circuitBreaker.run(booleanSupplier, throwable -> handleErrorCase());
        if(allProductsInStock) {
            orderService.placeOrder(orderDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else {
            return ResponseEntity.ok("Order failed, one of the products in the order is not in stock");
        }
    }

    private Boolean handleErrorCase() {
        return false;
    }
}
