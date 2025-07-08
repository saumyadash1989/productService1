package com.example.productservice1.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTo {
    private long id;
    private String name;
    private String description;
    private String category;
    private double price;
    private String image;


    public static ProductResponseDTo getProductDto(Product product){
        ProductResponseDTo productDTo = new ProductResponseDTo();
        productDTo.setId(product.getId());
        productDTo.setName(product.getNaame());
        productDTo.setCategory(product.getCategory().getName());
        productDTo.setPrice(product.getPrice());
        productDTo.setImage(product.getImage());
        return productDTo;

    }
}
