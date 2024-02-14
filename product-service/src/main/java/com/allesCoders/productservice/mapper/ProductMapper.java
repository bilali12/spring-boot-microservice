package com.allesCoders.productservice.mapper;

import com.allesCoders.productservice.dto.ProductRequest;
import com.allesCoders.productservice.dto.ProductResponse;
import com.allesCoders.productservice.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product fromProductRequest(ProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

}
