package com.example.productservice1.services;

import com.example.productservice1.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public List<Product> getProducts();
    public Product getProductById(String id);
}
