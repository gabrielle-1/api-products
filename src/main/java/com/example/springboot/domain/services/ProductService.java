package com.example.springboot.domain.services;

import com.example.springboot.domain.dtos.ProductRecordDto;
import com.example.springboot.domain.models.ProductModel;
import com.example.springboot.domain.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<ProductModel> saveProduct(ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        var productSaved = productRepository.save(productModel);
        return productSaved;
    }

    public Flux<ProductModel> getAllProducts() {
        Flux<ProductModel> products = this.productRepository.findAll();
        /*
        if (!products.isEmpty()) {
            for (ProductModel product : products) {
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }
         */
        return products;
    }

    /*  Without use Hateoas
        public List<ProductModel> getAllProducts() {
            return this.productRepository.findAll();
        }


    public Mono<ProductModel> getOneProduct(UUID id) {
        Mono<ProductModel> product = this.productRepository.findById(id);
        // if (product.isEmpty()) return null;
        // product.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
        // return product.get();
        return product;
    }

    public Object updateProduct(UUID id, ProductRecordDto productRecordDto) {
        Mono<ProductModel> product = this.productRepository.findById(id);
        /*
        if (product.isEmpty()) {
            return null;
        }
        var productModel = product.get();

        var productModel = product;
        BeanUtils.copyProperties(productRecordDto, productModel);
        var productSaved = this.productRepository.save(productModel.);
        return productSaved;
    }

    public boolean deleteProduct(UUID id) {
        Mono<ProductModel> product = this.productRepository.findById(id);
        if (product.isEmpty()) {
            return false;
        }
        this.productRepository.delete(product.get());
        return true;
    }

     */
}
