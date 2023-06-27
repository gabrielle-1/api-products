package com.example.springboot.services;

import com.example.springboot.controllers.ProductController;
import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductModel saveProduct(ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        var productSaved = productRepository.save(productModel);
        return productSaved;
    }

    public List<ProductModel> getAllProducts() {
        List<ProductModel> products = this.productRepository.findAll();
        if (!products.isEmpty()) {
            for (ProductModel product : products) {
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }
        return products;
    }

    public Object getOneProduct(UUID id) {
        Optional<ProductModel> product = this.productRepository.findById(id);
        if (product.isEmpty()) return null;
        product.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
        return product.get();
    }

    public Object updateProduct(UUID id, ProductRecordDto productRecordDto) {
        Optional<ProductModel> product = this.productRepository.findById(id);
        if (product.isEmpty()) {
            return null;
        }
        var productModel = product.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        var prductSaved = this.productRepository.save(productModel);
        return prductSaved;
    }

    public boolean deleteProduct(UUID id) {
        Optional<ProductModel> product = this.productRepository.findById(id);
        if (product.isEmpty()) {
            return false;
        }
        this.productRepository.delete(product.get());
        return true;
    }
}
