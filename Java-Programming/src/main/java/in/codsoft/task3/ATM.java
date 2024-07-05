package in.codsoft.task3;

import in.codsoft.util.ConsoleUtil;

import java.util.Scanner;

public class ATM {
    private final BankAccount account;
    private final Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println(ConsoleUtil.colorText("Thank you for using the ATM!", ConsoleUtil.GREEN));
                    running = false;
                    break;
                default:
                    System.out.println(ConsoleUtil.colorText("Invalid choice. Please try again.", ConsoleUtil.RED));
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println(ConsoleUtil.colorText("\nATM Menu:", ConsoleUtil.CYAN));
        System.out.println(ConsoleUtil.colorText("1. Withdraw", ConsoleUtil.BLUE));
        System.out.println(ConsoleUtil.colorText("2. Deposit", ConsoleUtil.BLUE));
        System.out.println(ConsoleUtil.colorText("3. Check Balance", ConsoleUtil.BLUE));
        System.out.println(ConsoleUtil.colorText("4. Exit", ConsoleUtil.BLUE));
        System.out.print(ConsoleUtil.colorText("Enter your choice: ", ConsoleUtil.CYAN));
    }

    private void withdraw() {
        System.out.print(ConsoleUtil.colorText("Enter amount to withdraw: ", ConsoleUtil.YELLOW));
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (amount > 0 && account.getBalance() >= amount) {
            account.withdraw(amount);
            System.out.println(ConsoleUtil.colorText("Withdrawal successful. Please collect your cash.", ConsoleUtil.GREEN));
        } else {
            System.out.println(ConsoleUtil.colorText("Insufficient balance or invalid amount.", ConsoleUtil.RED));
        }
    }

    private void deposit() {
        System.out.print(ConsoleUtil.colorText("Enter amount to deposit: ", ConsoleUtil.YELLOW));
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (amount > 0) {
            account.deposit(amount);
            System.out.println(ConsoleUtil.colorText("Deposit successful.", ConsoleUtil.GREEN));
        } else {
            System.out.println(ConsoleUtil.colorText("Invalid amount.", ConsoleUtil.RED));
        }
    }

    private void checkBalance() {
        System.out.println(ConsoleUtil.colorText("Your current balance is: $" + account.getBalance(), ConsoleUtil.YELLOW));
    }
}
