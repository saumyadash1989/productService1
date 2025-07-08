package com.example.productservice1.services;

import com.example.productservice1.configuration.RestTemplateConfiguration;
import com.example.productservice1.models.FakeStoreProductDto;
import com.example.productservice1.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplateConfiguration restTemplate1) {
        this.restTemplate = restTemplate1.getRestTemplate();
    }
    @Override
    public List<Product> getProducts() {
        return List.of();
    }
    @Override
    public Product getProductById(String id) {
       FakeStoreProductDto fakeStoreProductDto= restTemplate.getForObject("https://fakestoreapi.com/products/" +id, FakeStoreProductDto.class);
       return fakeStoreProductDto.toProduct();
    }


}
