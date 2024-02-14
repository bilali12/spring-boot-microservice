package com.allesCoders.productservice.web;

import com.allesCoders.productservice.dto.ProductRequest;
import com.allesCoders.productservice.dto.ProductResponse;
import com.allesCoders.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(CREATED)
    public String createProduct(@RequestBody ProductRequest request) {
        productService.createProduct(request);
        return "Product created successfully";
    }
    @GetMapping
    @ResponseStatus(OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
}
