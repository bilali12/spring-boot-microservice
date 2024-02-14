package com.allesCoders.inventoryservice.mapper;

import com.allesCoders.inventoryservice.dto.InventoryRequest;
import com.allesCoders.inventoryservice.dto.InventoryResponse;
import com.allesCoders.inventoryservice.entity.Inventory;
import org.springframework.stereotype.Service;

@Service
public class InventoryMapper {

    public Inventory fromInventoryRequest(InventoryRequest inventoryRequest) {
        return Inventory.builder()
                .skuCode(inventoryRequest.getSkuCode())
                .quantity(inventoryRequest.getQuantity())
                .build();
    }

    public InventoryResponse toInventoryResponse(Inventory inventory) {
        return InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity() > 0) // if the quantity is greater than 0 it's will mention true otherwise false
                .quantity(inventory.getQuantity())
                .build();
    }
}
