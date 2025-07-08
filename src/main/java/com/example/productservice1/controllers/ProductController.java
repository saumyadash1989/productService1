package com.example.productservice1.controllers;

import com.example.productservice1.models.Product;
import com.example.productservice1.models.ProductResponseDTo;
import com.example.productservice1.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTo> getProduct(@PathVariable String id) {
        Product product = productService.getProductById(id);
       ProductResponseDTo productResponseDTo = ProductResponseDTo.getProductDto(product);
        ResponseEntity responseEntity= new ResponseEntity(productResponseDTo, HttpStatus.BAD_GATEWAY);
        //Here we are return model dto object to client but its a java object but
        //when we call localhost:8080/products/1 in browser then it returns json file how it does
        //it happens due to jackson who does the serialisation and deserialisation
        return responseEntity;

    }
}
