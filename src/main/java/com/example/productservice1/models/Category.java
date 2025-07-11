package com.example.productservice1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category extends BaseModel {

    private String name;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
