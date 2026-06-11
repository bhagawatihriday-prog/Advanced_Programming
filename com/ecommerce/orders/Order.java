package com.ecommerce.orders;

public abstract class Order {

    protected String orderId;
    protected double amount;

    public Order(String orderId, double amount) {

        this.orderId = orderId;
        this.amount = amount;
    }

    public abstract double calculateTotal();

    public String getOrderId() {

        return orderId;
    }
}