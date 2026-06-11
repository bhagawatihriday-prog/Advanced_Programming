package com.ecommerce.service;

import com.ecommerce.orders.Order;
import com.ecommerce.payment.PaymentMethod;
import com.ecommerce.notification.NotificationService;
import com.ecommerce.storage.OrderStorage;

public class OrderService {

    private PaymentMethod payment;
    private NotificationService notification;
    private OrderStorage storage;

    public OrderService(
            PaymentMethod payment,
            NotificationService notification,
            OrderStorage storage
    ) {

        this.payment = payment;
        this.notification = notification;
        this.storage = storage;
    }

    public void processOrder(Order order) {

        double total = order.calculateTotal();

        payment.pay(total);

        storage.save(order);

        notification.notifyUser(
                "Order "
                        + order.getOrderId()
                        + " processed successfully"
        );
    }
}