package com.example.productservice1.controllers;

import com.example.productservice1.dtos.CreateProductRequestDto;
import com.example.productservice1.dtos.ExceptionDto;
import com.example.productservice1.exceptions.ProductNotFoundException;
import com.example.productservice1.models.Product;
import com.example.productservice1.dtos.ProductResponseDTo;
import com.example.productservice1.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTo> getProduct(@PathVariable long id) throws ProductNotFoundException {
        ResponseEntity responseEntity;

            Product product = productService.getProductById(id);
            ProductResponseDTo productResponseDTo = ProductResponseDTo.getProductDto(product);
            responseEntity = new ResponseEntity(productResponseDTo, HttpStatus.BAD_GATEWAY);
         //Here we are return model dto object to client but its a java object but
        //when we call localhost:8080/products/1 in browser then it returns json file how it does
        //it happens due to jackson who does the serialisation and deserialisation

        return responseEntity;

    }
    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDTo>> getProducts() {
        List<ProductResponseDTo>  productResponseDTos= new ArrayList<>();
        List<Product> products = productService.getProducts();
        for(Product product : products) {
            ProductResponseDTo productResponseDTo = ProductResponseDTo.getProductDto(product);
            productResponseDTos.add(productResponseDTo);


        }
        return  ResponseEntity.ok(productResponseDTos);

    }
    @PostMapping("/")
    public ResponseEntity<ProductResponseDTo> createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        Product product=productService.createProduct(createProductRequestDto.getName(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getImage(),
                createProductRequestDto.getPrice(),
                createProductRequestDto.getCategory());
        ResponseEntity<ProductResponseDTo> responseEntity;
        responseEntity = new ResponseEntity(product, HttpStatus.CREATED);
        return responseEntity;

    }

}
