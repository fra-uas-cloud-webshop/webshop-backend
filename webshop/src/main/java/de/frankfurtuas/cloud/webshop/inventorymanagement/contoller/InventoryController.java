package de.frankfurtuas.cloud.webshop.inventorymanagement.contoller;

import de.frankfurtuas.cloud.webshop.inventorymanagement.service.InventoryService;
import de.frankfurtuas.cloud.webshop.productmanagement.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
