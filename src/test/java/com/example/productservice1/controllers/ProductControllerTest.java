package com.example.productservice1.controllers;

import com.example.productservice1.dtos.ProductResponseDTo;
import com.example.productservice1.exceptions.ProductNotFoundException;
import com.example.productservice1.models.Category;
import com.example.productservice1.models.Product;
import com.example.productservice1.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Date;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {
    @Autowired

    private ProductController productController;
    @MockitoBean
    @Qualifier("pruductServiceImpl")
    private ProductService  productService;
   @Test
    public void testGetProductByIdReturnsProductResponseDto() throws ProductNotFoundException {
       Product  product = new Product();
       product.setId(1);
       product.setPrice(12.45);
       product.setImage("image.url");
       product.setTitle("title");
       product.setDescrption("description");
       Category category= new Category();
       category.setId(1);
       category.setName("category");
       category.setCreatedAt(new Date());
       product.setCategory(category);
       when(productService.getProductById(1L)).thenReturn(product);

      ProductResponseDTo productResponseDTo= productController.getProduct(1L);
       Assertions.assertEquals(1L, productResponseDTo.getId());
       Assertions.assertEquals(12.45, productResponseDTo.getPrice());
       Assertions.assertEquals("image.url", productResponseDTo.getImage());
       Assertions.assertEquals(category.getName(), productResponseDTo.getCategory());
       Assertions.assertEquals("title",productResponseDTo.getName());
       Assertions.assertEquals("description",productResponseDTo.getDescription());

    }
    @Test
    public void testGetProductByIdReturnsNull() throws ProductNotFoundException {
       when(productService.getProductById(1L)).thenReturn(null);
       ProductResponseDTo productResponseDTo= productController.getProduct(1L);
       Assertions.assertNull(productResponseDTo);

    }

}
