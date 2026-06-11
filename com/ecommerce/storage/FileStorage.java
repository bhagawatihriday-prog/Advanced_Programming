package com.ecommerce.storage;

import com.ecommerce.orders.Order;

public class FileStorage implements OrderStorage {
    public void save(Order order) {
        System.out.println("Saved to File: " + order.getOrderId());
    }
}