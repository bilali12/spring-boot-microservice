package com.allesCoders.inventoryservice;

import com.allesCoders.inventoryservice.dto.InventoryRequest;
import com.allesCoders.inventoryservice.service.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(InventoryService inventoryService) {
		return args -> {
			InventoryRequest inventoryRequest = new InventoryRequest("mac", 2);
			InventoryRequest inventoryRequest1 = new InventoryRequest("samsung", 0);
			inventoryService.createInventory(inventoryRequest);
			inventoryService.createInventory(inventoryRequest1);

		};
	}
}
