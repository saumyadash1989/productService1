package com.example.productservice1.services;


import com.example.productservice1.dtos.FakeStoreCreateProductDto;
import com.example.productservice1.dtos.FakeStoreProductDto;
import com.example.productservice1.exceptions.ProductNotFoundException;
import com.example.productservice1.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakestoreProductService")
public class FakeStoreProductService implements ProductService {
  private final String url="https://fakestoreapi.com/products/";
    RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    @Override
    public List<Product> getProducts() {
      FakeStoreProductDto[] list= restTemplate.getForObject(url, FakeStoreProductDto[].class);
      List<Product>  products=new ArrayList<>();
      for(FakeStoreProductDto fakeStoreProductDto:list){
         products.add( fakeStoreProductDto.toProduct());


      }
      return products;

    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        return List.of();
    }

    @Override
    public Product getProductById(long  id) throws ProductNotFoundException {

        FakeStoreProductDto fakeStoreProductDto1= restTemplate.getForObject(url+id, FakeStoreProductDto.class);
         if(fakeStoreProductDto1==null){
             throw new ProductNotFoundException("Product not found");

         }
       return fakeStoreProductDto1.toProduct();
    }

    @Override
    public Product createProduct(String name,String description,String image,double  price,String category) {
        FakeStoreCreateProductDto fakeStoreCreateProductDto= new FakeStoreCreateProductDto();
        fakeStoreCreateProductDto.setTitle(name);
        fakeStoreCreateProductDto.setDescription(description);
        fakeStoreCreateProductDto.setImage(image);
        fakeStoreCreateProductDto.setPrice(price);
        fakeStoreCreateProductDto.setCategory(category);

          FakeStoreProductDto fakeStoreProductDto= restTemplate.postForObject(url , fakeStoreCreateProductDto,FakeStoreProductDto.class);
         return  fakeStoreProductDto.toProduct();


    }


}
