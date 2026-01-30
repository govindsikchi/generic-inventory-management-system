package com.company.inventory.model;

import java.util.concurrent.atomic.AtomicInteger;

public class StockRecord {
    private final AtomicInteger quantity = new AtomicInteger(0);

    public int add(int qty) {
        return quantity.addAndGet(qty);
    }

    public boolean remove(int qty) {
        while (true) {
            int current = quantity.get();
            if (current < qty) return false;
            if (quantity.compareAndSet(current, current - qty)) {
                return true;
            }
        }
    }

    public int getQuantity() {
        return quantity.get();
    }
}
