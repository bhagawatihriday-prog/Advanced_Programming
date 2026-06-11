package com.ecommerce.storage;

import com.ecommerce.orders.Order;

public interface OrderStorage {

    void save(Order order);
}