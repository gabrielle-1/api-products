package com.example.springboot.controllers;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    private int port = 8080;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateProductSuccess() {
        // Arrange
        String name = "Valid Name";
        BigDecimal value = new BigDecimal("1500.00");
        LocalDateTime createdAt = LocalDateTime.parse("2023-06-30T00:00:00");
        LocalDateTime updatedAt = LocalDateTime.parse("2023-06-30T00:00:00");

        ProductRecordDto productRecordDto = new ProductRecordDto(name, value, createdAt, updatedAt);

        // Act
        ResponseEntity<ProductModel> response = this.restTemplate.postForEntity(createURLWithPort("/products"), productRecordDto, ProductModel.class);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().getName());
    }

    @Test
    public void testCreateProductFailure() {
        // Arrange
        String name = "";
        BigDecimal value = new BigDecimal("1500.00");
        LocalDateTime createdAt = LocalDateTime.parse("2023-06-30T00:00:00");
        LocalDateTime updatedAt = LocalDateTime.parse("2023-06-30T00:00:00");

        ProductRecordDto productRecordDto = new ProductRecordDto(name, value, createdAt, updatedAt);

        // Act
        ResponseEntity<ProductModel> response = this.restTemplate.postForEntity(createURLWithPort("/products"), productRecordDto, ProductModel.class);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody().getName());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + this.port + uri;
    }
}
