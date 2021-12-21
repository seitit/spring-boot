package com.seiti.spring.inventoryservice.service;

import com.seiti.spring.inventoryservice.entity.Inventory;
import com.seiti.spring.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public Inventory findBySkuCode(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode).orElseThrow(() -> new RuntimeException("Cannot find product by skuCode"+skuCode));
    }
}
