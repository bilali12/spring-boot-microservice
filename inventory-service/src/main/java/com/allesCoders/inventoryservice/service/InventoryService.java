package com.allesCoders.inventoryservice.service;

import com.allesCoders.inventoryservice.dto.InventoryRequest;
import com.allesCoders.inventoryservice.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    List<InventoryResponse> isInStock(List<String> skuCode);
    void createInventory(InventoryRequest inventoryRequest);
}
