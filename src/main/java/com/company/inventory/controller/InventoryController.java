package com.company.inventory.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.inventory.model.InventoryItem;
import com.company.inventory.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    // 1. Create Item (NEW)
    @PostMapping
    public void create(@RequestBody InventoryItem item) {
        service.createItem(item.getItemId(), item);
    }

    // 2. Inward (Add Stock)
    @PostMapping("/{itemId}/inward")
    public void inward(@PathVariable String itemId,
                       @RequestParam int qty) {
        service.addStock(itemId, qty);
    }

    // 3. Outward (Reduce Stock)
    @PostMapping("/{itemId}/outward")
    public void outward(@PathVariable String itemId,
                        @RequestParam int qty) {
        service.removeStock(itemId, qty);
    }

    // 4. Update Item Details
    @PutMapping("/{itemId}")
    public void update(@PathVariable String itemId,
                       @RequestBody InventoryItem item) {
        service.updateItem(itemId, item);
    }

    // 5. Delete Item
    @DeleteMapping("/{itemId}")
    public void delete(@PathVariable String itemId) {
        service.deleteItem(itemId);
    }
}
