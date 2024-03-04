package BankApp;

import javax.swing.*;
import java.time.LocalDate;

public class BankMain {
    private static String firstName;
    private static String lastName;
    private static String pin;
    private static String accountNumber;
    private static double amount;
    private static final LocalDate date = LocalDate.now();
    private static String accountName;
    static Bank bank = new Bank("XCHANGE BANK");

    public static void main(String[] args) {
        firstMenu();

    }

    public static void display(String message) {
        JOptionPane.showMessageDialog(null, message);
    }


    public static String collectInput(String input) {
        return JOptionPane.showInputDialog(null, input);
    }

    public static void firstMenu() {
        display("XCHANGE BANK");
        display("\"Money Farm\"");
        String choice = collectInput("""
                HOME PAGE
                Select an option
                1.Register Account
                2.Exit
                                 
                 """);
        switch (choice) {
            case "1" -> {
                register();
            }
            case "2" -> {
                exit();
            }
            default -> {
                display("Invalid input");
                firstMenu();
            }
        }
    }


    public static void register() {
        display("Register Account");
        firstName = collectInput("Enter First Name");
        if (firstName.isEmpty()) {
            display("First Name is empty");
            register();
        }
        lastName = collectInput("Enter Last Name");
        if (lastName.isEmpty()) {
            display("Last Name is empty");
            register();
        }
        pin = collectInput("Enter Pin");
        if (pin.isEmpty()) {
            display("Invalid Pin, Please try again");
            register();
        }
        bank.register(firstName, lastName, pin);
        String account_Number = bank.register(firstName, lastName, pin);
        display("Account Successfully Created");
        display("Your Account Number is " + account_Number);

        miniMenu();
    }

    public static void miniMenu() {
        String choose = collectInput("""
                Select an Option
                1.Deposit
                2.Withdraw
                3.Transfer
                4.Check Balance
                5.Return to Home Page
                0.Exit
                """);

        switch (choose) {
            case "1" -> {
                deposit();
            }
            case "2" -> {
                withdraw();
            }
            case "3" -> {
                transfer();
            }
            case "4" -> {
                checkBalance();
            }
            case "5" -> {
                firstMenu();
            }
            case "0" -> {
                exit();
            }
            default -> {
                display("Invalid option");
                miniMenu();
            }
        }
    }

    public static void deposit() {
        display("Deposit");
        try {
            accountNumber = collectInput("Enter Account Number");
            amount = Double.parseDouble(collectInput("Enter Amount"));
            bank.deposit(accountNumber, amount);
            bank.checkBalance(accountNumber, pin);
            display("""
                    Credit Alert!!!
                    Your account\s""" + " " + accountNumber
                    + " " + "has been credited with $" + bank.checkBalance(accountNumber,pin) +
                    "\n " + "on " + date + """
                    """);
            miniMenu();

        } catch (IllegalArgumentException e) {
            display(e.getMessage());
            miniMenu();
        }
    }

    public static void withdraw() {
        display("Withdraw");
        try {
            accountNumber = collectInput("Enter Account Number");
            amount = Double.parseDouble(collectInput("Enter Amount"));
            pin = collectInput("Enter PIN Number");
            bank.withdraw(amount, accountNumber, pin);
            bank.checkBalance(accountNumber, pin);
            display("""
                    Debit Alert!!!
                    Your account""" + " " +accountNumber+ " " + "has been debited with $" + amount +
                    "\n" + "on" + date + """
                   """);
            display("Your Account Balance is :"+ bank.checkBalance(accountNumber, pin));
            miniMenu();
        }
        catch (IllegalArgumentException e){
            display("Error : " + e.getMessage());
            miniMenu();}
    }

    public static void transfer() {
        display("Transfer");
        try {
            accountNumber = collectInput("Enter Account Number");
            amount = Double.parseDouble(collectInput("Enter Amount"));
            String recipientAccountNumber = collectInput("Enter Recipient Account Number");
            bank.transfer(amount, accountNumber, recipientAccountNumber, pin);
            display("""
                    Transfer Alert!!!
                    Your account has been debited with $""" + amount + """
                    """ + date + """
                   """);
            display("Your Account Balance is :"+ bank.checkBalance(accountNumber, pin));
            miniMenu();}
        catch (IllegalArgumentException e) {
            display("Error : " + e.getMessage());}
        miniMenu();}



    public  static void checkBalance(){
        display("Check Balance");
        try {
            accountNumber = collectInput("Enter Account Number");
            pin = collectInput("Enter PIN Number");
            String balance = String.valueOf(bank.checkBalance(accountNumber, pin));
            display("Balance: " + balance);
        }
        catch (IllegalArgumentException e) {
            display("Error: " + e.getMessage());
        }
        miniMenu();}

    private static void exit() {
            display("Thanks for using Xchange Bank");
        }
    }
