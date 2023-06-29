package com.example.springboot.domain.repositories;

import com.example.springboot.domain.models.ProductModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<ProductModel, UUID> {
}
