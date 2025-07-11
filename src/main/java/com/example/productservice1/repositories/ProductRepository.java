package com.example.productservice1.repositories;

import com.example.productservice1.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {

    Product findById(long id);
    public Product save(Product product);
    public  Product saveAndFlush(Product product);
}
