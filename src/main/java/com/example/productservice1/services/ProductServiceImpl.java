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
import java.util.Optional;

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
      List<Product>  prodcuts =  productRepository.findAll();
       return prodcuts;
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
       List<Category> category=categoryRepository.findByName(categoryName);
       List<Product>  prodcuts =  productRepository.findByCategory(category.get(0));
      // List<Product> products= productRepository.findByCategory_Name(categoryName);
      //List<Product> products=productRepository.getProductsByCategoryName(categoryName);
//        List<Product> products= productRepository.getAllProductsByCategoryNameNative(categoryName);

       return prodcuts;
    }



    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
      Optional< Product > product= productRepository.findById(id);

      if(product.isEmpty()){
          throw new ProductNotFoundException("Product not found");

      }
      Product product1 =product.get();
//      Category category=product1.getCategory();
//      product1.setCategory(category);

        return product1;
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
