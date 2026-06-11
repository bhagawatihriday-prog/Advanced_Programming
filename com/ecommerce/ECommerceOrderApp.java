package com.ecommerce;

import com.ecommerce.orders.*;
import com.ecommerce.payment.*;
import com.ecommerce.notification.*;
import com.ecommerce.storage.*;
import com.ecommerce.service.*;

import java.util.Scanner;

public class ECommerceOrderApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ORDER ID
        System.out.println("Enter Order ID:");
        String orderId = sc.nextLine();

        // AMOUNT
        System.out.println("Enter Order Amount:");
        double amount = sc.nextDouble();

        // ORDER TYPE
        System.out.println("\nSelect Order Type:");
        System.out.println("1. Regular Order");
        System.out.println("2. Discounted Order");
        System.out.println("3. Priority Order");

        int orderChoice = sc.nextInt();

        Order order;

        switch (orderChoice) {

            case 1:
                order = new RegularOrder(orderId, amount);
                break;

            case 2:

                System.out.println("Enter Discount Percentage:");

                double discount = sc.nextDouble();

                order = new DiscountedOrder(
                        orderId,
                        amount,
                        discount
                );

                break;

            case 3:

                System.out.println("Enter Priority Fee:");

                double fee = sc.nextDouble();

                order = new PriorityOrder(
                        orderId,
                        amount,
                        fee
                );

                break;

            default:
                System.out.println("Invalid Order Type");
                sc.close();
                return;
        }

        // PAYMENT METHOD
        System.out.println("\nSelect Payment Method:");
        System.out.println("1. Credit Card");
        System.out.println("2. UPI");
        System.out.println("3. Wallet");

        int paymentChoice = sc.nextInt();

        PaymentMethod payment;

        switch (paymentChoice) {

            case 1:
                payment = new CreditCardPayment();
                break;

            case 2:
                payment = new UPIPayment();
                break;

            case 3:
                payment = new WalletPayment();
                break;

            default:
                System.out.println("Invalid Payment Method");
                sc.close();
                return;
        }

        // NOTIFICATION METHOD
        System.out.println("\nSelect Notification Method:");
        System.out.println("1. Email");
        System.out.println("2. SMS");
        System.out.println("3. Push Notification");

        int notificationChoice = sc.nextInt();

        NotificationService notification;

        switch (notificationChoice) {

            case 1:
                notification = new EmailNotification();
                break;

            case 2:
                notification = new SMSNotification();
                break;

            case 3:
                notification = new PushNotification();
                break;

            default:
                System.out.println("Invalid Notification Method");
                sc.close();
                return;
        }

        // STORAGE METHOD
        System.out.println("\nSelect Storage Method:");
        System.out.println("1. Database Storage");
        System.out.println("2. File Storage");

        int storageChoice = sc.nextInt();

        OrderStorage storage;

        switch (storageChoice) {

            case 1:
                storage = new DatabaseStorage();
                break;

            case 2:
                storage = new FileStorage();
                break;

            default:
                System.out.println("Invalid Storage Method");
                sc.close();
                return;
        }

        // ORDER SERVICE
        OrderService service =
                new OrderService(
                        payment,
                        notification,
                        storage
                );

        // PROCESS ORDER
        service.processOrder(order);

        sc.close();
    }
}