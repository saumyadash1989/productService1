package com.example.productservice1.repositories;

import com.example.productservice1.models.Category;
import com.example.productservice1.models.Product;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,String> {


    Optional<Product> findById(long s);


    public Product save(Product product);

    public Product saveAndFlush(Product product);

    public List<Product> findByCategory_Name(String name);
    public List<Product> findByCategory(Category category);
    @Query("select p from Product  p where p.category.name=:categoryName")
    public List<Product> getProductsByCategoryName(String categoryName);
    @Query(value = "select * from Product where category_id in(select category_id from Category  where name =:categoryName)",nativeQuery = true)
    public List<Product> getAllProductsByCategoryNameNative(String categoryName);
}