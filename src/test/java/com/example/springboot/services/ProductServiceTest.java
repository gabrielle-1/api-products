package com.example.springboot.services;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import com.example.springboot.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveProduct() {
        // Arrange
        var productId = UUID.randomUUID();
        String name = "Test Product";
        BigDecimal value = new BigDecimal("1500.00");
        LocalDateTime createdAt = LocalDateTime.parse("2023-06-30T00:00:00");
        LocalDateTime updatedAt = LocalDateTime.parse("2023-06-30T00:00:00");

        ProductRecordDto productRecordDto = new ProductRecordDto(name, value, createdAt, updatedAt);
        ProductModel productModel = new ProductModel();
        productModel.setIdProduct(productId);
        productModel.setName(name);
        productModel.setValue(value);
        productModel.setCreatedAt(createdAt);
        productModel.setUpdatedAt(updatedAt);

        when(productRepository.save(any(ProductModel.class))).thenReturn(productModel);

        // Act
        ProductRecordDto savedProduct = productService.saveProduct(productRecordDto);
        BeanUtils.copyProperties(savedProduct, productModel);

        // Assert
        assertEquals(productId, productModel.getIdProduct());
        assertEquals(name, productModel.getName());
        assertEquals(value, productModel.getValue());
        assertEquals(createdAt, productModel.getCreatedAt());
        assertEquals(updatedAt, productModel.getUpdatedAt());

        verify(productRepository, times(1)).save(any(ProductModel.class));
    }
}
