package com.allesCoders.orderservice.service;


import com.allesCoders.orderservice.dto.InventoryResponse;
import com.allesCoders.orderservice.dto.OrderRequest;
import com.allesCoders.orderservice.entity.Order;
import com.allesCoders.orderservice.entity.OrderLinesItems;
import com.allesCoders.orderservice.exception.ProductNotExistException;
import com.allesCoders.orderservice.exception.SomeProductsNotInStockException;
import com.allesCoders.orderservice.mapper.OrderMapper;
import com.allesCoders.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    @Override
    public void placeOder(OrderRequest orderRequest) {
        final String URI = "http://localhost:8082/api/v1/inventorys";
        Order order = orderMapper.fromOrderRequest(orderRequest);
        List<Boolean> orderValids = new ArrayList<>();

        List<String> skuCodes = order.getOrderLinesItemsList().stream()
                .map(OrderLinesItems::getSkuCode)
                .toList();

        for (String skuCode : skuCodes) {
            System.out.println(skuCode);
        }

        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri(URI, uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();


        if (inventoryResponses.length !=  0) {
            for (InventoryResponse inventoryResponse: inventoryResponses) {
                for (OrderLinesItems orderItem: order.getOrderLinesItemsList()) {
                    if (inventoryResponse.getSkuCode().equalsIgnoreCase(orderItem.getSkuCode())) {
                        orderValids.add(orderItem.getQuantity() <= inventoryResponse.getQuantity());
                    }
                }
            }

            boolean allProductInStocks = orderValids.stream().allMatch(Boolean::booleanValue);

            if (allProductInStocks) {
                orderRepository.save(order);
                log.info("Order created successfully");
            }else {
                throw new SomeProductsNotInStockException("Quantities of products not available, please decrease the quantities...");
            }
        }
         else {
            throw new ProductNotExistException("Product(s) does not exit in the stock");
        }

    }
}

