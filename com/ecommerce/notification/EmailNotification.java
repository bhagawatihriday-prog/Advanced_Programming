package com.ecommerce.notification;

public class EmailNotification implements NotificationService {
    public void notifyUser(String message) {
        System.out.println("Email: " + message);
    }
}