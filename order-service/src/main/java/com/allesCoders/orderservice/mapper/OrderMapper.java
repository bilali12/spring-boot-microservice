package com.allesCoders.orderservice.mapper;

import com.allesCoders.orderservice.dto.OrderLinesItemsDTO;
import com.allesCoders.orderservice.dto.OrderRequest;
import com.allesCoders.orderservice.entity.Order;
import com.allesCoders.orderservice.entity.OrderLinesItems;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class OrderMapper {
    public Order fromOrderRequest(OrderRequest request) {
        List<OrderLinesItems> orderLinesItems= request.getOrderLinesItemsDtoList().stream()
                .map(this::fromOrderLineDTO).toList();
        return Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLinesItemsList(orderLinesItems)
                .build();
    }

    private OrderLinesItems fromOrderLineDTO(OrderLinesItemsDTO orderLinesItemsDTO) {
        return OrderLinesItems.builder()
                .price(orderLinesItemsDTO.getPrice())
                .quantity(orderLinesItemsDTO.getQuantity())
                .skuCode(orderLinesItemsDTO.getSkuCode())
                .build();
    }
}
