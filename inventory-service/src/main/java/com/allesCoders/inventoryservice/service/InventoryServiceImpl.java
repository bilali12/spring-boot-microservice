package com.allesCoders.inventoryservice.service;

import com.allesCoders.inventoryservice.dto.InventoryRequest;
import com.allesCoders.inventoryservice.dto.InventoryResponse;
import com.allesCoders.inventoryservice.mapper.InventoryMapper;
import com.allesCoders.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
    @Transactional(readOnly = true)
    @Override
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory -> inventoryMapper.toInventoryResponse(inventory)).toList();
    }

    @Override
    public void createInventory(InventoryRequest inventoryRequest) {
        inventoryRepository.save(inventoryMapper.fromInventoryRequest(inventoryRequest));
        log.info("Added successfully");
    }
}
