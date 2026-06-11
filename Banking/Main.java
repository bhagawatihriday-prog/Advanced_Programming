package Banking;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of accounts: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {

            System.out.println("\n--- Account " + (i + 1) + " ---");

            System.out.println("Choose Account Type (1: Savings, 2: Current): ");
            int choice = sc.nextInt();

            if (choice != 1 && choice != 2) {
                System.out.println("Invalid choice. Try again...");
                i--; // repeat same account
                continue;
            }

            System.out.print("Enter Account Number: ");
            int accNo = sc.nextInt();
            sc.nextLine(); // consume newline

            String name;

            while (true) {
                System.out.print("Enter Owner Name: ");
                name = sc.nextLine();

                try {
                    if (name == null || name.trim().isEmpty()) {
                        throw new IllegalArgumentException("Owner name cannot be empty");
                    }
                    break; // valid input
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            double balance;

            while (true) {
                System.out.print("Enter Initial Balance: ");
                try {
                    balance = sc.nextDouble();

                    if (balance < 0) {
                        throw new IllegalArgumentException("Balance cannot be negative");
                    }

                    break; // valid input

                } catch (InputMismatchException e) {
                    System.out.println("Error: Please enter a valid number");
                    sc.nextLine(); // clear invalid input

                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            Account acc;

            switch (choice) {

                case 1:
                    System.out.print("Enter Interest Rate: ");
                    double rate = sc.nextDouble();
                    acc = new SavingsAccount(accNo, name, balance, rate);
                    break;

                case 2:
                    System.out.print("Enter Overdraft Limit: ");
                    double limit = sc.nextDouble();
                    acc = new CurrentAccount(accNo, name, balance, limit);
                    break;

                default:
                    throw new IllegalStateException("Unexpected value");
            }

            // -------- OPERATIONS FOR THIS ACCOUNT --------
            System.out.print("Enter deposit amount: ");
            double dep = sc.nextDouble();
            acc.deposit(dep);

            System.out.print("Enter withdrawal amount: ");
            double wd = sc.nextDouble();

            try {
                acc.withdraw(wd);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            // -------- DISPLAY IMMEDIATELY --------
            System.out.println("\n--- Account Details ---");
            acc.display();
            System.out.println("=======================");
        }

        sc.close();
    }
}