package com.example.productservice1.controllers;

import com.example.productservice1.dtos.ProductResponseDTo;
import com.example.productservice1.dtos.SearchRequestDto;
import com.example.productservice1.models.Product;
import com.example.productservice1.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SearchController {

    private SearchService searchService;
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    @PostMapping("/search")
  public Page<ProductResponseDTo> search(@RequestBody SearchRequestDto searchRequestDto){
      Page<Product>  productPage=  searchService.search(searchRequestDto.getQuery(),
                 searchRequestDto.getPageNumber(),
                 searchRequestDto.getPageSize(),
                searchRequestDto.getSortParam());
      return getProductResponseDtos(productPage);


  }
  @GetMapping("/getSearch")
  public Page<ProductResponseDTo> getSearch(@RequestParam String query ,
                                            @RequestParam int pageNumber,
                                            @RequestParam int pageSize,
                                            @RequestParam String sortParam
                                           ){
      Page<Product>  productPage=  searchService.search(query,pageNumber,pageSize,sortParam);
            
      return getProductResponseDtos(productPage);
  }
  public Page<ProductResponseDTo> getProductResponseDtos(Page<Product> productPage){
      List<Product> productList=productPage.getContent();
      List<ProductResponseDTo>  productResponseDTos= new ArrayList<>();

      for(Product product:productList){
          ProductResponseDTo productResponseDTo=ProductResponseDTo.getProductDto(product);
          productResponseDTos.add(productResponseDTo);

      }

      return new PageImpl<>(productResponseDTos,productPage.getPageable(),productPage.getTotalElements());
  }

}
