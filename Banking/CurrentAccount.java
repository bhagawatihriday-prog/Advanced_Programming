package Banking;

public class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(int accNo, String name, double balance, double overdraftLimit) {
        super(accNo, name, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid withdrawal");
        }

        if (amount > getBalance() + overdraftLimit) {
            throw new IllegalArgumentException("Overdraft limit exceeded");
        }

        setBalance(getBalance() - amount);
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Account Type: Current");
        System.out.println("Overdraft Limit: " + overdraftLimit);
    }
}
