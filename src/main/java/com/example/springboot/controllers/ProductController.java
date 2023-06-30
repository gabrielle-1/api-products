package com.example.springboot.controllers;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.services.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductRecordDto> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        var productSaved = this.productService.saveProduct(productRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> products = this.productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") @NotNull @Positive UUID id) {
        Object product = this.productService.getOneProduct(id);
        if (product == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") @NotNull @Positive UUID id,
                                                @RequestBody @Valid ProductRecordDto productRecordDto) {

        var product = this.productService.updateProduct(id, productRecordDto);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") @NotNull @Positive UUID id) {
        var product = this.productService.deleteProduct(id);
        if (!product) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The product could not be deleted.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
}
