package com.example.productservice1.services;

import com.example.productservice1.configuration.RestTemplateConfiguration;
import com.example.productservice1.dtos.FakeStoreCreateProductDto;
import com.example.productservice1.dtos.FakeStoreProductDto;
import com.example.productservice1.exceptions.ProductNotFoundException;
import com.example.productservice1.models.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {






  private  String url="https://fakestoreapi.com/products/";
   private  RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplateConfiguration restTemplate1) {
        this.restTemplate = restTemplate1.getRestTemplate();
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
    public Product getProductById(long  id) throws ProductNotFoundException {

        FakeStoreProductDto fakeStoreProductDto= restTemplate.getForObject(url +id, FakeStoreProductDto.class);
         if(fakeStoreProductDto==null){
             throw new ProductNotFoundException("Product not found");

         }
       return fakeStoreProductDto.toProduct();
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
