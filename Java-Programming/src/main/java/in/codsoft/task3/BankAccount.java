package in.codsoft.task3;

import lombok.Getter;

/**
 * BankAccount class represents a simple bank account with balance.
 */
@Getter
public class BankAccount {
    /**
     * -- GETTER --
     * Retrieves the current balance of the account.
     *
     * @return Current balance of the account.
     */
    private double balance;

    /**
     * Constructs a BankAccount instance with an initial balance.
     *
     * @param initialBalance Initial balance for the account.
     */
    public BankAccount(double initialBalance) {
        if (initialBalance > 0) {
            this.balance = initialBalance;
        }
    }

    /**
     * Withdraws a specified amount from the account if sufficient balance is available.
     *
     * @param amount Amount to withdraw from the account.
     */
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
        }
    }

    /**
     * Deposits a specified amount into the account.
     *
     * @param amount Amount to deposit into the account.
     */
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}
