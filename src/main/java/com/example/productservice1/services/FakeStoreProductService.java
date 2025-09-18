package com.example.productservice1.services;


import com.example.productservice1.dtos.FakeStoreCreateProductDto;
import com.example.productservice1.dtos.FakeStoreProductDto;
import com.example.productservice1.exceptions.ProductNotFoundException;
import com.example.productservice1.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakestoreProductService")
public class FakeStoreProductService implements ProductService {
  private final String url="https://fakestoreapi.com/products/";
    RestTemplate restTemplate;
    RedisTemplate<String ,Object> redisTemplate;
    public FakeStoreProductService(RestTemplate restTemplate,RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;

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
       Product productFromCache= (Product) redisTemplate.opsForValue().get(String.valueOf(id));
       if(productFromCache!=null){
           return productFromCache;

       }
        FakeStoreProductDto fakeStoreProductDto1= restTemplate.getForObject(url+id, FakeStoreProductDto.class);
         if(fakeStoreProductDto1==null){
             throw new ProductNotFoundException("Product not found");

         }
     Product productFromFakeStore=fakeStoreProductDto1.toProduct();
         redisTemplate.opsForValue().set(String.valueOf(id),productFromFakeStore);
         return productFromFakeStore;
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
