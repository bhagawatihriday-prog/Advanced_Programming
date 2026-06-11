package com.ecommerce.orders;

public class RegularOrder extends Order {
    public RegularOrder(String id, double amount) {
        super(id, amount);
    }

    public double calculateTotal() {
        return amount;
    }
}