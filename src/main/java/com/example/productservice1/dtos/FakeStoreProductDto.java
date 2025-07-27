package com.example.productservice1.dtos;

import com.example.productservice1.models.Category;
import com.example.productservice1.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private long id;
    private String title;
    private String description;
    private double price;
    private String category;
    private String image;

    public Product toProduct() {
        Product product = new Product();
        product.setId(this.id);
        Category category = new Category();
        category.setName(this.getCategory());
        product.setCategory(category);
        product.setPrice(this.getPrice());
        product.setDescrption(this.getDescription());
        product.setImage(this.getImage());
        product.setTitle(this.getTitle());
        return product;

    }

}
