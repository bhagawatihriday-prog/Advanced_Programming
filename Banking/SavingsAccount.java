package Banking;

public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(int accNo, String name, double balance, double interestRate) {
        super(accNo, name, balance);
        this.interestRate = interestRate;
    }

    public double calculateInterest() {
        return getBalance() * interestRate / 100;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Account Type: Savings");
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Interest: " + calculateInterest());
    }
}
