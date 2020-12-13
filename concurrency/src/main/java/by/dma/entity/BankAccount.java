package by.dma.entity;

/**
 * Simple bank accoiunt with synchronizatin.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
    }

    public synchronized void withdraw(double amount) {
        balance -= amount;
    }

    public void transferTo(BankAccount destination, double amount) {
        synchronized (this) {
            sleepAWhile();
            synchronized (destination) {
                withdraw(amount);
                destination.deposit(amount);
            }
        }
    }

    protected void sleepAWhile() {

    }

}
