package in.codsoft.task3;

import in.codsoft.util.ConsoleUtil;

import java.util.Scanner;

/**
 * ATM class simulates an ATM machine interacting with a BankAccount.
 */
public class ATM {
    private final BankAccount account;
    private final Scanner scanner;

    /**
     * Constructs an ATM instance with a given BankAccount.
     *
     * @param account The BankAccount associated with the ATM.
     */
    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the ATM interaction.
     */
    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    performWithdrawal();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    exitATM();
                    running = false;
                    break;
                default:
                    ConsoleUtil.printColored("Invalid choice. Please try again.", ConsoleUtil.RED);
                    break;
            }
        }
    }

    /**
     * Displays the ATM menu.
     */
    private void printMenu() {
        ConsoleUtil.printColored("\nATM Menu:", ConsoleUtil.CYAN);
        ConsoleUtil.printColored("1. Withdraw", ConsoleUtil.BLUE);
        ConsoleUtil.printColored("2. Deposit", ConsoleUtil.BLUE);
        ConsoleUtil.printColored("3. Check Balance", ConsoleUtil.BLUE);
        ConsoleUtil.printColored("4. Exit", ConsoleUtil.BLUE);
        ConsoleUtil.printColored("Enter your choice: ", ConsoleUtil.CYAN);
    }

    /**
     * Prompts the user for their choice and returns it.
     *
     * @return User's choice as an integer.
     */
    private int getUserChoice() {
        int choice = 0;
        boolean validInput = false;

        do {
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                validInput = true;
            } catch (NumberFormatException e) {
                ConsoleUtil.printColored("Invalid input. Please enter a number.", ConsoleUtil.RED);
            }
        } while (!validInput);

        return choice;
    }

    /**
     * Performs a withdrawal operation.
     */
    private void performWithdrawal() {
        ConsoleUtil.printColored("Enter amount to withdraw: ", ConsoleUtil.YELLOW);
        double amount = getValidAmount();

        if (amount > 0 && account.getBalance() >= amount) {
            account.withdraw(amount);
            ConsoleUtil.printColored("Withdrawal successful. Please collect your cash.", ConsoleUtil.GREEN);
        } else {
            ConsoleUtil.printColored("Insufficient balance or invalid amount.", ConsoleUtil.RED);
        }
    }

    /**
     * Performs a deposit operation.
     */
    private void performDeposit() {
        ConsoleUtil.printColored("Enter amount to deposit: ", ConsoleUtil.YELLOW);
        double amount = getValidAmount();

        if (amount > 0) {
            account.deposit(amount);
            ConsoleUtil.printColored("Deposit successful.", ConsoleUtil.GREEN);
        } else {
            ConsoleUtil.printColored("Invalid amount.", ConsoleUtil.RED);
        }
    }

    /**
     * Checks and displays the current balance.
     */
    private void checkBalance() {
        ConsoleUtil.printColored("Your current balance is: $" + account.getBalance(), ConsoleUtil.YELLOW);
    }

    /**
     * Exits the ATM interaction.
     */
    private void exitATM() {
        ConsoleUtil.printColored("Thank you for using the ATM!", ConsoleUtil.GREEN);
    }

    /**
     * Prompts the user for an amount and returns it after validation.
     *
     * @return Valid amount entered by the user.
     */
    private double getValidAmount() {
        double amount = 0;
        boolean validInput = false;

        do {
            try {
                amount = Double.parseDouble(scanner.nextLine().trim());
                validInput = amount > 0;
                if (!validInput) {
                    ConsoleUtil.printColored("Amount must be greater than zero.", ConsoleUtil.RED);
                }
            } catch (NumberFormatException e) {
                ConsoleUtil.printColored("Invalid input. Please enter a valid number.", ConsoleUtil.RED);
            }
        } while (!validInput);

        return amount;
    }
}
