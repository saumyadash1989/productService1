package com.example.productservice1.repositories;

import com.example.productservice1.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CateGoryRepository extends JpaRepository<Category, Long> {

    public Category save(Category category);
}
