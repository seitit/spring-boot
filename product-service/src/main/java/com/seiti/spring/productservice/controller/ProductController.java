package com.seiti.spring.productservice.controller;

import com.seiti.spring.productservice.entity.Product;
import com.seiti.spring.productservice.repository.ProductRepository;
import com.seiti.spring.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable String id) {
        return productService.findById(id).map(product -> ResponseEntity.ok(product)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity saveProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
