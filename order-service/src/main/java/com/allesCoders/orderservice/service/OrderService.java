package com.allesCoders.orderservice.service;

import com.allesCoders.orderservice.dto.OrderRequest;
import org.springframework.stereotype.Service;


public interface OrderService {
    String placeOder(OrderRequest request);
}
