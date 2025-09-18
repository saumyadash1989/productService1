package com.example.productservice1.services;

import com.example.productservice1.dtos.SearchRequestDto;
import com.example.productservice1.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

public interface SearchService {
    public Page<Product> search(String query , int pageNumber, int pageSize,String sortParam);
}
