package de.frankfurtuas.cloud.webshop.inventorymanagement.controller;

import de.frankfurtuas.cloud.webshop.inventorymanagement.service.InventoryService;
import de.frankfurtuas.cloud.webshop.productmanagement.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long id, @RequestParam int stock) {
        return ResponseEntity.ok(inventoryService.updateStock(id, stock));
    }
}
