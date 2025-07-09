package com.example.productservice1.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDto {
    private String name;
     private String title;

    private String description;
    private String category;
    private double price;
    private String image;
}
