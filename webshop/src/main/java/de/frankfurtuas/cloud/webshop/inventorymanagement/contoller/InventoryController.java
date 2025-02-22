package de.frankfurtuas.cloud.webshop.inventorymanagement.contoller;

import de.frankfurtuas.cloud.webshop.common.controller.BaseController;
import de.frankfurtuas.cloud.webshop.inventorymanagement.model.Inventory;
import de.frankfurtuas.cloud.webshop.inventorymanagement.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The inventory controller.
 */
@RestController
@RequestMapping("/api/inventory")
public class InventoryController extends BaseController {

    private final InventoryService inventoryService;

    /**
     * Constructor.
     *
     * @param inventoryService the inventory service
     */
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Get all inventories.
     */
    @GetMapping()
    public ResponseEntity<List<Inventory>> getAllInventories() {
        return ResponseEntity.ok(inventoryService.getAllInventories());
    }

    /**
     * Update the quantity of a product.
     *
     * @param inventoryId the inventory id
     * @param quantity the quantity
     */
    @PutMapping("/{inventoryId}")
    public ResponseEntity<Inventory> updateQuantity(@PathVariable Long inventoryId, @RequestParam Integer quantity) {
        return ResponseEntity.ok(inventoryService.updateQuantity(inventoryId, quantity));
    }
}
