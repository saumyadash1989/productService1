package com.example.productservice1.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateProductDto {

   private  String  title ;
           private double price ;
           private String category;
           private String image;
           private String description;


}
