package BankApp;

import java.util.ArrayList;
import java.util.List;

import static BankApp.BankMain.bank;


public class Bank {


    private List<Account> accountList = new ArrayList<>();
    public String bankName;

    public Bank(String BankName) {
        this.bankName = BankName;
    }

    public String register(String firstName, String lastName, String pin) {
        String fullName = firstName + " " + lastName;
        Account account = new Account(fullName, generateAccountNumber(), pin);
        String accountNumber = bank.generateAccountNumber();
        accountList.add(account);
        return accountNumber;
    }
    public String generateAccountNumber() {
        return accountList.size() + " ";
    }

    public Account findAccount(String accountNumber) {
        for (Account data : accountList) {
            if (data.getAccountNumber().equals(accountNumber))
                return data;
        }
        throw new IllegalArgumentException("CANNOT FIND ACCOUNT");
    }

    public void deposit(String accountNumber,double amount) {
        if (amount > 0) {
            findAccount(accountNumber).deposit(amount);
        }
        else {
            throw new IllegalArgumentException("Invalid amount");
        }

    }
    public void withdraw(double amount, String accountNumber, String pin) {
        findAccount(accountNumber).withdraw(amount, pin);
        }



    public void transfer(double amount, String fromAccount, String toAccount, String pin) {
        Account accountSender = findAccount(fromAccount);
        accountSender.withdraw(amount, pin);
        Account accountReceiver = findAccount(toAccount);
        accountReceiver.deposit(amount);
    }

    public double checkBalance(String accountNumber, String pin) {
        return findAccount(accountNumber).CheckBalance(pin);
    }

    public int numberOfRegisteredAccount() {
        return accountList.size();
    }
}











