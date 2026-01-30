package com.company.inventory.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.company.inventory.exception.InsufficientStockException;
import com.company.inventory.exception.ItemNotFoundException;
import com.company.inventory.model.InventoryItem;
import com.company.inventory.model.StockRecord;
import com.company.inventory.store.InventoryStore;

@Service
public class InventoryService {

    private final InventoryStore store;

    public InventoryService(InventoryStore store) {
        this.store = store;
    }

    // 1. Inward (Add Stock)
    public void addStock(String itemId, int qty) {
        store.getOrCreateStock(itemId).add(qty);
    }

    // 2. Outward (Reduce Stock)
    public void removeStock(String itemId, int qty) {
        StockRecord record = Optional.ofNullable(store.getStock(itemId))
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));

        boolean success = record.remove(qty);
        if (!success) {
            throw new InsufficientStockException("Insufficient stock");
        }
    }

    // NEW: Create Item
    public void createItem(String itemId, InventoryItem item) {
        store.saveItem(itemId, item);
        store.getOrCreateStock(itemId); // initialize stock safely
    }

    // 3. Update Item Details
    public void updateItem(String itemId, InventoryItem updatedItem) {
        InventoryItem existing = Optional.ofNullable(store.getItem(itemId))
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));

        existing.setName(updatedItem.getName());
        existing.setCategory(updatedItem.getCategory());
        existing.setAttributes(updatedItem.getAttributes());
    }

    // 4. Delete Item
    public void deleteItem(String itemId) {
        store.deleteItem(itemId);
    }
}
