package com.example.productservice1.intigrationTesting;
import com.example.productservice1.controllers.ProductController;
import com.example.productservice1.dtos.FakeStoreProductDto;
import com.example.productservice1.dtos.ProductResponseDTo;
import com.example.productservice1.exceptions.ProductNotFoundException;
import com.example.productservice1.helpers.ProductHelper;
import com.example.productservice1.models.Product;
import com.example.productservice1.repositories.CateGoryRepository;
import com.example.productservice1.repositories.ProductRepository;
import com.example.productservice1.services.FakeStoreProductService;
import com.example.productservice1.services.ProductService;
import com.example.productservice1.services.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductServiceTest {
    @Autowired
    MockMvc mockMvc;
;    @MockitoBean
    ProductController productController;
@MockitoBean
    CateGoryRepository cateGoryRepository;
    @MockitoBean
    ProductService productService;

    @Test
    public void createProductEndPoint() throws Exception {
        List<Product> ProductsList=new ArrayList<>();
     FakeStoreProductDto fakeStoreProductDto= ProductHelper.getFakeStoreProductDto(1L,"title",12.56,"image.url",
                "category","description");
        ObjectMapper objectMapper = new ObjectMapper();
       String requestBody= objectMapper.writeValueAsString(fakeStoreProductDto);
        FakeStoreProductDto savedfakeStoreProductDto= ProductHelper.getFakeStoreProductDto(1L,"title",12.56,"image.url",
                "category","description");

        when(productService.createProduct("title", "description",
              "image.url",12.56,"category")).thenReturn(savedfakeStoreProductDto.toProduct());
        mockMvc.perform(post("/products/").
                content(requestBody).contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());





    }
    @Test
    public void testGetProductByIdReturnProductApi() throws Exception {

        FakeStoreProductDto savedfakeStoreProductDto= ProductHelper.getFakeStoreProductDto(1L,"title",12.56,"image.url",
                "category","description");

        when(productService.getProductById(1)).thenReturn(savedfakeStoreProductDto.toProduct());
        mockMvc.perform(post("/products/1").
                        content(String.valueOf(1L)).contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isMethodNotAllowed());
    }

}
