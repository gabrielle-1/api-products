package com.example.springboot.repositories;

import com.example.springboot.models.ProductModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface ProductRepository extends ReactiveCrudRepository<ProductModel, UUID> {
}
