package com.allesCoders.productservice.service;

import com.allesCoders.productservice.dto.ProductRequest;
import com.allesCoders.productservice.dto.ProductResponse;
import com.allesCoders.productservice.entity.Product;
import com.allesCoders.productservice.mapper.ProductMapper;

import com.allesCoders.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService{
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    @Override
    public void createProduct(ProductRequest request) {
        Product product = productMapper.fromProductRequest(request);
        productRepository.save(product);
        log.info("Product {} is created...", product.getId());
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toProductResponse).toList();
    }
}
