package com.example.springboot.services;

import com.example.springboot.controllers.ProductController;
import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductRecordDto saveProduct(ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        var productSavedModel = productRepository.save(productModel);

        BeanUtils.copyProperties(productSavedModel, productRecordDto);

        return productRecordDto;
    }

    public List<ProductModel> getAllProducts() {
        var products = this.productRepository.findAll();

        if (!products.isEmpty()) {
            for (ProductModel product : products) {
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }

        return products;
    }

    /*  Without use Hateoas
        public List<ProductModel> getAllProducts() {
            return this.productRepository.findAll();
        }
    */

    public ProductModel getOneProduct(UUID id) {
        var product = this.productRepository.findById(id);
        if (product.isEmpty()) return null;
        product.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
        return product.get();
    }

    public Object updateProduct(UUID id, ProductRecordDto productRecordDto) {
        var product = this.productRepository.findById(id);
        if (product.isEmpty()) {
            return null;
        }
        var productModel = product.get();

        BeanUtils.copyProperties(productRecordDto, productModel);
        var productSaved = this.productRepository.save(productModel);
        return productSaved;
    }

    public boolean deleteProduct(UUID id) {
        var product = this.productRepository.findById(id);
        if (product.isEmpty()) {
            return false;
        }
        this.productRepository.delete(product.get());
        return true;
    }
}
