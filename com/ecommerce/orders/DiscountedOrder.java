package com.ecommerce.orders;

public class DiscountedOrder extends Order {

    private double discountPercent;

    public DiscountedOrder(
            String orderId,
            double amount,
            double discountPercent
    ) {

        super(orderId, amount);
        this.discountPercent = discountPercent;
    }

    @Override
    public double calculateTotal() {

        return amount - (amount * discountPercent / 100);
    }
}