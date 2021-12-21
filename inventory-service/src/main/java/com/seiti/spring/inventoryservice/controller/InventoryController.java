package com.seiti.spring.inventoryservice.controller;

import com.seiti.spring.inventoryservice.entity.Inventory;
import com.seiti.spring.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{skuCode}")
    Boolean isInStock(@PathVariable String skuCode){
        Inventory inventory = inventoryService.findBySkuCode(skuCode);
        return inventory.getStock() > 0;
    }
}
