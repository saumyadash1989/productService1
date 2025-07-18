package com.example.productservice1.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.repository.cdi.Eager;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {

    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    @Fetch(FetchMode.JOIN)
    private List<Product> products;

}
