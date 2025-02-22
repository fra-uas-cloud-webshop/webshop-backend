package de.frankfurtuas.cloud.webshop.inventorymanagement.mapper;

import de.frankfurtuas.cloud.webshop.inventorymanagement.dto.InventoryDTO;
import de.frankfurtuas.cloud.webshop.inventorymanagement.model.Inventory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The inventory mapper.
 */
public class InventoryMapper {

    /**
     * Method to map a list of Inventory entities to a list of InventoryDTOs.
     *
     * @param inventories the list of inventories
     */
    public static List<InventoryDTO> toInventoryDTOList(List<Inventory> inventories) {
        return inventories.stream()
                .map(inventory -> new InventoryDTO(
                        inventory.getProduct().getName(),
                        inventory.getProduct().getCategory(),
                        inventory.getQuantity()))
                .collect(Collectors.toList());
    }

    /**
     * Method to map an Inventory entity to an InventoryDTO.
     *
     * @param inventory the inventory entity
     */
    public static InventoryDTO toInventoryDTO(Inventory inventory) {
        return new InventoryDTO(
                inventory.getProduct().getName(), inventory.getProduct().getCategory(), inventory.getQuantity());
    }
}