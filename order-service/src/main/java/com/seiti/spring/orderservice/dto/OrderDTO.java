package com.seiti.spring.orderservice.dto;

import com.seiti.spring.orderservice.entity.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private List<OrderLineItems> orderLineItemsList;
}
