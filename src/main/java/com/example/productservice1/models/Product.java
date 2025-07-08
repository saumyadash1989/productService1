package com.example.productservice1.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private long id;
    private String naame;
    private String descrption;
    private double price;
      private Category category;
      private String title;
      private String image;




}
