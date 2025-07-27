package com.example.productservice1.helpers;

import com.example.productservice1.dtos.FakeStoreProductDto;

public class ProductHelper {
   public static FakeStoreProductDto getFakeStoreProductDto(long id,String title,double price,
                                                            String imageUrl,String category,String description) {
       FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
       fakeStoreProductDto.setId(id);
       fakeStoreProductDto.setTitle(title);
       fakeStoreProductDto.setPrice(price);
       fakeStoreProductDto.setImage(imageUrl);
       fakeStoreProductDto.setCategory(category);
       fakeStoreProductDto.setDescription(description);
       return fakeStoreProductDto;
   }
}
