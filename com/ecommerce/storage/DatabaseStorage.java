package com.ecommerce.storage;

import com.ecommerce.orders.Order;

public class DatabaseStorage implements OrderStorage {

    @Override
    public void save(Order order) {

        System.out.println(
                "Order saved to database: "
                        + order.getOrderId()
        );
    }
}