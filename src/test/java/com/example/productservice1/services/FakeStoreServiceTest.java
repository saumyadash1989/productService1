package com.example.productservice1.services;

import com.example.productservice1.dtos.FakeStoreProductDto;
import com.example.productservice1.exceptions.ProductNotFoundException;
import com.example.productservice1.helpers.ProductHelper;
import com.example.productservice1.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class FakeStoreServiceTest {
    RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
    RedisTemplate redisTemplate = Mockito.mock(RedisTemplate.class);
    FakeStoreProductService fakeStoreProductServic= new FakeStoreProductService(restTemplate,redisTemplate);
    @Test
    public void testGetProductByIdReturnsProduct() throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto= ProductHelper.getFakeStoreProductDto(1L,"title",12.56,"image.url",
                "category","description");
        when(restTemplate.getForObject("https://fakestoreapi.com/products/1", FakeStoreProductDto.class)).thenReturn(fakeStoreProductDto);
        Product fakeStoreProductDto1;
        fakeStoreProductDto1 = fakeStoreProductServic.getProductById(1L);
        Assertions.assertEquals("title",fakeStoreProductDto1.getTitle());
        Assertions.assertEquals(12.56,fakeStoreProductDto1.getPrice());

    }
    @Test
    public void testGetProductByIdThrowsProductNotFoundException() throws ProductNotFoundException {
        when(restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDto.class)).thenReturn(null);
        Assertions.assertThrows(ProductNotFoundException.class, () -> fakeStoreProductServic.getProductById(1));
    }
    @Test
    public void testCreateProductAndReturnProductwithId(){
        FakeStoreProductDto fakeStoreProductDto= ProductHelper.getFakeStoreProductDto(1L,"title",12.56,"image.url",
                "category","description");
        when(restTemplate.postForObject(eq("https://fakestoreapi.com/products/"),
                any(),eq(FakeStoreProductDto.class))).
                thenReturn(fakeStoreProductDto);
           Product product=fakeStoreProductServic.createProduct("title", "description",
                   "image.url",12.56,"category");
           Assertions.assertEquals("title",product.getTitle());
           Assertions.assertEquals(12.56,product.getPrice());



    }
    @Test
    public void testCreateProductVerifyApiCall(){
        FakeStoreProductDto fakeStoreProductDto= ProductHelper.getFakeStoreProductDto(1L,"title",12.56,"image.url",
                "category","description");
        when(restTemplate.postForObject(eq("https://fakestoreapi.com/products/"),
                any(),eq(FakeStoreProductDto.class))).
                thenReturn(fakeStoreProductDto);
        Product product=fakeStoreProductServic.createProduct("title", "description",
                "image.url",12.56,"category");
        verify(restTemplate,times(1)).postForObject(eq("https://fakestoreapi.com/products/"),
                any(),eq(FakeStoreProductDto.class));


    }
    @Test
    public void testGetAllProductsReturnsListOfProducts(){
       FakeStoreProductDto[] fakeStoreProductDtoList=new FakeStoreProductDto[2];
       fakeStoreProductDtoList[0]=ProductHelper.getFakeStoreProductDto(1L,"title",12.56,"image.url",
                "category","description");
        fakeStoreProductDtoList[1]= ProductHelper.getFakeStoreProductDto(2L,"2ndtitle",13.56,"2ndimage.url",
               "2ndcategory","2nddescription");
       when(restTemplate.getForObject("https://fakestoreapi.com/products/",FakeStoreProductDto[].class)).thenReturn(fakeStoreProductDtoList);
        List<Product> fakeStoreProductDtos=fakeStoreProductServic.getProducts();
        Assertions.assertEquals("title",fakeStoreProductDtos.get(0).getTitle());
        Assertions.assertEquals(12.56,fakeStoreProductDtos.get(0).getPrice());
        Assertions.assertEquals("2ndtitle",fakeStoreProductDtos.get(1).getTitle());
        Assertions.assertEquals(2,fakeStoreProductDtos.size());

    }

}
