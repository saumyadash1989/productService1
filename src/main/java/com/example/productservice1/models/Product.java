package com.example.productservice1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String descrption;
    private double price;
      @ManyToOne
      @JoinColumn(name = "category_id")
      private Category category;
      private String title;
      private String image;



}
