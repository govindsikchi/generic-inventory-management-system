package com.company.inventory.store;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.company.inventory.model.InventoryItem;
import com.company.inventory.model.StockRecord;

@Component
public class InventoryStore {

    // Item metadata storage
    private final ConcurrentHashMap<String, InventoryItem> items = new ConcurrentHashMap<>();

    // Stock storage
    private final ConcurrentHashMap<String, StockRecord> stock = new ConcurrentHashMap<>();

    // Save new item (Create)
    public void saveItem(String itemId, InventoryItem item) {
        items.put(itemId, item);
    }

    // Get item
    public InventoryItem getItem(String itemId) {
        return items.get(itemId);
    }

    // Get or create stock safely (thread-safe)
    public StockRecord getOrCreateStock(String itemId) {
        return stock.computeIfAbsent(itemId, k -> new StockRecord());
    }

    // Get stock
    public StockRecord getStock(String itemId) {
        return stock.get(itemId);
    }

    // Delete item & stock atomically
    public void deleteItem(String itemId) {
        items.remove(itemId);
        stock.remove(itemId);
    }
}
