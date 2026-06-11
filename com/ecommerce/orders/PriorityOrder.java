package com.ecommerce.orders;

public class PriorityOrder extends Order {

    private double priorityFee;

    public PriorityOrder(
            String orderId,
            double amount,
            double priorityFee
    ) {

        super(orderId, amount);

        this.priorityFee = priorityFee;
    }

    @Override
    public double calculateTotal() {

        return amount + priorityFee;
    }
}