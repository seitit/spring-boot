package com.seiti.spring.productservice.repository;

import com.seiti.spring.productservice.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
