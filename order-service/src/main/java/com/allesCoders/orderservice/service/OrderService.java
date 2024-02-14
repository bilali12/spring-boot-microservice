package com.allesCoders.orderservice.service;

import com.allesCoders.orderservice.dto.OrderRequest;
import org.springframework.stereotype.Service;


public interface OrderService {
    void placeOder(OrderRequest request);
}
