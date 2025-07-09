package com.example.productservice1.services;

import com.example.productservice1.exceptions.ProductNotFoundException;
import com.example.productservice1.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public List<Product> getProducts();
    public Product getProductById(long  id) throws ProductNotFoundException;
    public Product createProduct(String name,String description,String image,double  price,String category);
}
