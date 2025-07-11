package com.example.productservice1.services;

import com.example.productservice1.dtos.CreateProductDto;
import com.example.productservice1.exceptions.ProductNotFoundException;
import com.example.productservice1.models.Category;
import com.example.productservice1.models.Product;
import com.example.productservice1.repositories.CateGoryRepository;
import com.example.productservice1.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("pruductServiceImpl")
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    private CateGoryRepository categoryRepository;
    public ProductServiceImpl(ProductRepository productRepository,CateGoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }




    @Override
    public List<Product> getProducts() {


        return List.of();
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
       Product product= productRepository.findById(id);

        return product;
    }

    @Override
    public Product createProduct(String name, String description, String image, double price, String category) {
        Product product = new Product();
        Category category1 = new Category();
        category1.setName(category);
        category1.setCreatedAt(new Date());
      Category category2=  categoryRepository.save(category1);
        product.setCategory(category1);
        product.setImage(image
        );
        product.setPrice(price);
        product.setDescrption(description);
        product.setTitle(name);
       return  productRepository.save(product);


    }
}
