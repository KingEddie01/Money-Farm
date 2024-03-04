package BankApp;

import java.util.Objects;

public class Account {

    private int balance;

    private String accountNumber;
    private String accountName;
    private String pin;



    public Account(String accountName, String accountNumber,String pin) {

        this.accountName = accountName;
        this.pin = pin;
        this.accountNumber = accountNumber;

    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
        else {
            throw new IllegalArgumentException("Enter valid amount");
        }

    }

    public int CheckBalance(String pin) {
        if (Objects.equals(this.pin, pin)) {
            return balance;
        }
        throw new IllegalArgumentException(" INVALID PIN");
    }
    public double getBalance() {
        return balance;
    }
    public void updatePin(String oldPin, String newPin){
        validatePin(oldPin);
        this.pin = newPin;
    }

    public void validatePin(String pin){
        boolean isNotCorrectPin = !this.pin.equals(pin);
        if (isNotCorrectPin){
            throw new IllegalArgumentException("Invalid Pin");
        }
    }
    public void withdraw(double amount, String pin) {
        if (amount < balance && amount > 0) {
            balance -= amount;
        }
        else {
            throw new IllegalArgumentException("INSUFFICIENT BALANCE");
        }
    }


    public String getAccountNumber() {
        return this.accountNumber;
    }
    public String getPin(){
        return this.pin;
    }

}







