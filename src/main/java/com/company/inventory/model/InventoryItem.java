package com.company.inventory.model;

import java.util.Map;

public class InventoryItem {

    private String itemId;
    private String name;
    private String category;
    private Map<String, Object> attributes;

    public InventoryItem() {
    }

    public InventoryItem(String itemId, String name, String category,
                         Map<String, Object> attributes) {
        this.itemId = itemId;
        this.name = name;
        this.category = category;
        this.attributes = attributes;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
