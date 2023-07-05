package com.example.springboot.controllers;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Product API", description = "API for product management")
@RestController
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductModel.class)))
    })
    @PostMapping("/products")
    public ResponseEntity<ProductRecordDto> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        var productSaved = this.productService.saveProduct(productRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
    }

    @Operation(summary = "Retrieve all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recovered products",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductModel.class)))
    })
    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(@Parameter(hidden = true) Pageable pageable) {
        List<ProductModel> products = this.productService.getAllProducts(pageable).getContent();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @Operation(summary = "Get product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductModel.class))),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") @NotNull @Positive UUID id, @Parameter(hidden = true) @NotNull @Positive Pageable pageable) {
        Object product = this.productService.getOneProduct(id, pageable);
        if (product == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @Operation(summary = "Update product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductModel.class))),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") @NotNull @Positive UUID id,
                                                @RequestBody @Valid ProductRecordDto productRecordDto) {

        var product = this.productService.updateProduct(id, productRecordDto);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @Operation(summary = "Delete product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") @NotNull @Positive UUID id) {
        var product = this.productService.deleteProduct(id);
        if (!product) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The product could not be deleted.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
}
