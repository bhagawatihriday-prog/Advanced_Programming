package com.ecommerce.payment;

public class WalletPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Wallet");
    }
}