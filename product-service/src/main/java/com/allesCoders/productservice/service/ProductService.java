package com.allesCoders.productservice.service;

import com.allesCoders.productservice.dto.ProductRequest;
import com.allesCoders.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest request);
    List<ProductResponse> getAllProducts();

}
