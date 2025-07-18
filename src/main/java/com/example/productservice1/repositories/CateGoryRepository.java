package com.example.productservice1.repositories;

import com.example.productservice1.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CateGoryRepository extends JpaRepository<Category, Long> {

    public Category save(Category category);

   public List<Category> findByName(String categoryName);
}
